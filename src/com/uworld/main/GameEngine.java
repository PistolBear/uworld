/******************
 * UWorld File name: Author: PistolBear Created: Feb 19, 2015 Desc: Tags:
 */
package com.uworld.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.Renderer;
import javax.swing.SwingUtilities;

import com.uworld.actors.ActorPC;
import com.uworld.gui.UWSwingGui;
import com.uworld.zork.ZorkGameEngine;

/**
 * @author PistolBear
 */
public abstract class GameEngine implements Runnable, GameEngineInterface
{
   ///// FIELDS /////
   protected static boolean b_gameSaved = false;
   protected static boolean b_gameStarted = false;
   protected static boolean b_menuShowing = false;
   protected boolean b_paused = false;
   protected static UWSwingGui theGui;
   protected ActorPC player = null;
   protected static GameEngine ge_engine;
   protected Renderer renderer;
   protected boolean b_running = false;
   protected int i_frameCount = 0;
   protected int i_currentFPS = 0;
   /**
    * At the very most we will update the game this many times before a new
    * render. If you're worried about visual hitches more than perfect timing,
    * set this to 1.
    */
   int MAX_UPDATES_BEFORE_RENDER = 3;
   long serialVersionUID = 5556579242006622965L;
   double GAME_HERTZ = 30.0;

   /**
    * Calculate ns each frame should take per hertz.
    */
   double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;

   protected static String HELP_REQUEST_STRING = "*** HELP SCREEN ***\n Common commands:\n - exit: exits program" 
   + "\n - North/South/East/West: turn that direction, or go that way if you're facing it" 
   + "\n - Arrow Up/Down: move forward, backward\n - Arrow Left/Right: turn left or right" 
   + "\n - look/see/search <target>: investigate the target. No target = surroundings" 
   + "\n - use/throw/eat <item> <target>: perform the action with the object you have upon" 
   + " the target you specified.";

   protected static String FORWARD_REQUEST = "FORWARD_ACTION_REQUESTED";

   protected static String BACKWARD_REQUEST = "BACKWARD_ACTION_REQUESTED";

   protected static double TARGET_FPS = 60;

   protected static boolean NON_GRAPHICS_ENFORCED = false;
   
   public static final String DICE_INPUT = "DICE_INPUT";


   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameEngine#run()
    */
   @Override
   public void run()
   {
      // game loop calls the renderer when it's ready
      SwingUtilities.invokeLater(this);
      gameLoop();
   }

   /**
    * gameLoop() Checks that game is still running, state is ok, etc. Asks
    * renderer or text output to do their things. Updates ZorkGameEngine with a
    * call to GameEngineInterface.update(Object ... newStuff).
    */
   private final synchronized void gameLoop()
   {
      // We will need the last update time.
      double lastUpdateTime = System.nanoTime();

      // Store the last time we rendered.
      double lastRenderTime = System.nanoTime();

      // If we are able to get as high as this FPS, don't render again.
      final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

      // Simple way of finding FPS.
      int lastSecondTime = (int) (lastUpdateTime / 1000000000);

      while (b_running)
      {
         double now = System.nanoTime();
         int updateCount = 0;

         if (!b_paused)
         {
            // Do as many game updates as we need to, potentially playing
            // catchup.
            while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER)
            {
               ge_engine.update(GAME_LOOP_UPDATE_MSG);
               lastUpdateTime += TIME_BETWEEN_UPDATES;
               updateCount++;
            }

            

            // If for some reason an update takes forever, we don't want to
            // do an insane number of catching up.
            // If you were doing some sort of game that needed to keep EXACT
            // time, you would get rid of this.
            if (now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            // Render. To do so, we need to calculate interpolation for a
            // smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
//            requestGameRender(interpolation);
            lastRenderTime = now;

            // Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
               System.out.println("NEW SECOND " + thisSecond + " " + i_frameCount);
               i_currentFPS = i_frameCount;
               i_frameCount = 0;
               lastSecondTime = thisSecond;
            }

            // Yield until it has been at least the target time between
            // renders. This saves the CPU from hogging.
            while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
//               yield();

               // This stops the app from consuming all your CPU. It makes
               // this slightly less accurate, but is worth it.
               // You can remove this line and it will still work (better),
               // your CPU just climbs on certain OSes.
               try
               {
                  Thread.sleep(1);
               }
               catch (Exception e)
               {
                  System.out.println(e.getStackTrace());
               }

               now = System.nanoTime();
            }
         }
      }
   }
}