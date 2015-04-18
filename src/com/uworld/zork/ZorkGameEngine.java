package com.uworld.zork;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Renderer;
import javax.swing.SwingUtilities;

import com.uworld.actors.ActorPC;
import com.uworld.actors.POM;
import com.uworld.gui.UWSwingGui;
import com.uworld.main.GameInterface;

public class ZorkGameEngine extends Thread implements GameInterface
{
   private static boolean b_gameSaved = false;

   private static boolean b_gameStarted = false;

   private static boolean b_menuShowing = false;

   private boolean b_paused = false;

   private static UWSwingGui theGui;

   private ActorPC player = null;

   private static GameInterface ge_engine;

   private Renderer renderer;

   private boolean b_running = false;

   private int i_frameCount = 0;

   private int i_currentFPS = 0;

   public ZorkGameEngine()
   {
      // theGui = new UWSwingGui();
      player = new POM()
      {
         int i_facing = 0;

         @Override
         public long getLevel()
         {
            return i_level;
         }

         public int getFacing()
         {
            return i_facing;
         }

         public int turnLeft()
         {
            if (i_facing == 0)
               return (i_facing = 3);
            else
               return --i_facing;
         }

         public int turnRight()
         {
            if (i_facing == 3)
               return (i_facing = 0);
            else
               return ++i_facing;
         }

         public int turnDir(int dir)
         {
            return (i_facing = dir);
         }

         @Override
         public void setFacing(String s)
         {
            switch (s)
            {
               case "north":
                  i_facing = 0;
                  break;
               case "east":
                  i_facing = 1;
                  break;
               case "south":
                  i_facing = 2;
                  break;
               case "west":
                  i_facing = 3;
                  break;
            }
         }
         
      };
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#update()
    */
   @Override
   public void update()
   {

   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#togglePaused()
    */
   @Override
   public boolean togglePaused()
   {
      b_paused = !b_paused;
      return b_paused;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#showMenu()
    */
   @Override
   public Object showMenu()
   {
      // return theGui.showMenuDialog();
      return "Menu is not yet implemented";
   }

   public static boolean isGameStarted()
   {
      return b_gameStarted;
   }

   public static boolean isMenuShowing()
   {
      return b_menuShowing;
   }

   public static boolean isGameSaved()
   {
      return b_gameSaved;
   }

   /**
    * @return the targetFps
    */
   public static double getTargetFps()
   {
      return TARGET_FPS;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#getI_currentFPS()
    */
   @Override
   public int getCurrentFPS()
   {
      return i_currentFPS;
   }

   private void requestGameRender(float interpolation)
   {
      // Doesn't really do anything.
      if (!renderer.getComponent().isVisible())
         renderer.getComponent().setVisible(true);

   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#run()
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
    * call to ZorkGameEngine.update(Bundle). Rocks out.
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
               ge_engine.update();
               lastUpdateTime += TIME_BETWEEN_UPDATES;
               updateCount++;
            }

            // log(Level.INFO, "Update: " + updateCount + " at " + now + ".");

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
            requestGameRender(interpolation);
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
               yield();

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

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameInterface#getGui()
    */
   @Override
   public UWSwingGui getGui()
   {
      return theGui;
   }

   public static GameInterface getGameEngine()
   {
      if (ge_engine == null)
      {
         ge_engine = new ZorkGameEngine();
      }

      return ge_engine;
   }

   /**
    * @see GameInterface.handleTokens() Primarily used in the parsing of text
    *      input with the ZorkEngine. handleTokens should launch dialogs, notify
    *      listeners, and generally be the primary input to the ZorkGameEngine.
    */
   @Override
   public String handleTokens(ArrayList<String> tokens)
   {
      String response = "";
      for (String s : tokens)
      {
         // Exit command
         if (s.trim().equals("exit"))
         {
            // Ask if player wants to save progress first?
            response = askPlayerToSave();

            // Return
            response += EXIT_COMMAND;

            break;
         }

         else if (s.trim().equals("help"))
         {
            response = GameInterface.HELP_REQUESTED_ZORK;
            break;
         }

         else if (s.contains("north") || s.contains("south") || s.contains("east") || s.contains("west"))
         {
            response = directionRequested(0);
         }

         else
         {

         }
      }

      return response;
   }

   private String directionRequested(String s)
   {
      String retString = null;

      switch (s)
      {
         case "north":
         case "south":
         case "east":
         case "west":
            player.setFacing(s);
            break;
            
         default:
            player.setFacing("north");
            break;
      }

      return retString;
   }

   /**
    * @return
    */
   private String getPlayerFacingAsString()
   {
      int i = player.getFacing();
      
      switch (i)
      {
         case 0:
            return "north";
         case 1:
            return "east";
         case 2:
            return "south";
         case 3:
            return "west";
         default:
            return "none";
      }
   }

   /**
    * @param s
    * @return
    */
   private String directionRequested(int i)
   {
      String retString = null;
      switch (i)
      {
         case KeyEvent.VK_KP_UP:
         case KeyEvent.VK_UP:
            break;

         case KeyEvent.VK_DOWN:
         case KeyEvent.VK_KP_DOWN:
            break;

         case KeyEvent.VK_LEFT:
         case KeyEvent.VK_KP_LEFT:
            break;

         case KeyEvent.VK_RIGHT:
         case KeyEvent.VK_KP_RIGHT:
            break;

         default:
            retString = null;
      }
      return retString;
   }

   /**
    * @return
    */
   private String askPlayerToSave()
   {
      return "Game Saved Probably!";
   }

}
