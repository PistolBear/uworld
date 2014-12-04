package com.uworld.main;

import javax.swing.JOptionPane;
import javax.swing.Renderer;
import javax.swing.SwingUtilities;

import com.uworld.gui.UWSwingGui;

public class GameEngine extends Thread
{
   private static boolean b_gameSaved = false;
   private static boolean b_gameStarted = false;
   private static boolean b_menuShowing = false;
   private boolean b_paused = false;
   
   private static UWSwingGui theGui;

   @SuppressWarnings("unused")
   private static final long serialVersionUID = 5556579242006622965L;
   private static final double GAME_HERTZ = 30.0;
   static final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ; // Calculate ns each frame should take per hertz.
   private GameEngine ge_engine;
   private Renderer renderer;
   private boolean b_running = false;
   private static final double TARGET_FPS = 60;
   private int i_frameCount = 0;
   private int i_currentFPS = 0;
   // At the very most we will update the game this many times before a new render.
   // If you're worried about visual hitches more than perfect timing, set this to 1.
   static final int MAX_UPDATES_BEFORE_RENDER = 3;

   
   GameEngine()
   {
      theGui = new UWSwingGui();
      init();
   }

   private void init()
   {
      b_gameStarted = true;
   }

   public void update()
   {
      
   }

   public boolean togglePaused()
   {
      b_paused = !b_paused;
      return b_paused;
   }

   public int showMenu()
   {
      return theGui.showMenuDialog();
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





   public static enum TopLevelMenuOptions {
      LOAD, SAVE, JOURNAL, CANCEL;
   }

   public static enum LoadMenuOptions {
      LOAD, DELETE, CANCEL;
   }

   public static enum SaveMenuOptions {
      SAVE, DELETE, CANCEL;
   }

   public static enum JournalMenuOptions {
      DELETE, CANCEL;
   }

   public static enum ConfirmDialogOptions {
      YES, NOPE;
   }

   /**
    * @return the targetFps
    */
   public static double getTargetFps()
   {
      return TARGET_FPS;
   }

   
   
   /**
    * @return the i_currentFPS
    */
   public int getI_currentFPS()
   {
      return i_currentFPS;
   }

   
   private void requestGameRender(float interpolation)
   {
      // Doesn't really do anything.
      if(!renderer.getComponent().isVisible())
         renderer.getComponent().setVisible(true);
      
   }

   
   /**
    * 
    * run()
    * 
    *   Probably doing this wrong.
    * 
    */
   @Override
   public void run()
   {
      // game loop calls the renderer when it's ready
      SwingUtilities.invokeLater(this);
      gameLoop();
   }
   
   
   /**
    * gameLoop()
    * 
    *  Checks that game is still running, state is ok, etc.
    *  Asks renderer or text output to do their things.
    *  Updates GameEngine with a call to GameEngine.update(Bundle).
    *  Rocks out.
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

   /**
    * @return
    */
   public UWSwingGui getGui()
   {
      return theGui;
   }

}
