package com.unrepentantwaiting.main;

import javax.swing.*;

import com.unrepentantwaiting.gui.GamePanel;
import com.unrepentantwaiting.gui.UWSwingGui;

import java.awt.*;
import java.awt.event.*;

public class Loop implements ActionListener
{
   private static final long serialVersionUID = 5556579242006622965L;
   private final UWSwingGui theGui; 
   private GamePanel gamePanel;
   private GameEngine gameEngine;
   private Renderer renderer;
   private JButton menuButton = new JButton("Start");
   private JButton quitButton = new JButton("Quit");
   private JButton pauseButton = new JButton("Pause");
   private JButton newGameButton = new JButton("New");
   private boolean b_running = false;
   private boolean b_paused = false;
   private static boolean b_useGui;
   private static final double TARGET_FPS = 60;
   private int i_frameCount = 0;
   private int i_currentFPS = 0;

   // Loop Constructor
   public Loop()
   {
      theGui = new UWSwingGui();
      theGui.AskABoolean("Some Question");
      
      gamePanel = new GamePanel(b_useGui);
      
      menuButton.addActionListener(this);
      quitButton.addActionListener(this);
      pauseButton.addActionListener(this);
      newGameButton.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e)
   {
      Object s = e.getSource();
      if (s == menuButton)
      {
         b_paused = !b_paused;
         if (b_paused)
         {
            menuButton.setText("Close Menu");
         }

         else
         {
            menuButton.setText("Menu");
            runGameLoop();
         }
      }

      else if (s == pauseButton)
      {
         b_paused = !b_paused;
         if (b_paused)
         {
            pauseButton.setText("Unpause");
         }
         
         else
         {
            pauseButton.setText("Pause");
         }
      }

      else if (s == quitButton)
      {
         System.exit(0);
      }
      
      else if (s == newGameButton)
      {
         newGame(b_useGui);
      }
      
      else if (s instanceof JOptionPane)
      {
         
      }
         
   }

   /**
    * @param b_useGui2
    */
   private void newGame(boolean b_useGui2)
   {
      // Create a character and do other first-time things, like
      // explaining the game.
      
      
      // run the game loop
   }


   /**
    * runGameLoop
    * 
    * Primary loop thread
    */
   public void runGameLoop()
   {
      gamePanel.setVisible(true);
      
      // game is its own thread
      Thread game = new Thread()
      {
         @Override
         public void run()
         {
            // game loop calls the renderer when it's ready
            gameLoop();
         }
      };

      
      // All of that is defined, so here's where we start the game:
      game.start();
   }

   // Only run this in another Thread!
   private final synchronized void gameLoop()
   {
      // This value would probably be stored elsewhere.
      final double GAME_HERTZ = 30.0;
      // Calculate how many ns each frame should take for our target game
      // hertz.
      final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
      // At the very most we will update the game this many times before a new
      // render.
      // If you're worried about visual hitches more than perfect timing, set
      // this to 1.
      final int MAX_UPDATES_BEFORE_RENDER = 3;
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
               updateLogic();
               lastUpdateTime += TIME_BETWEEN_UPDATES;
               updateCount++;
            }

            // If for some reason an update takes forever, we don't want to
            // do an insane number of catchups.
            // If you were doing some sort of game that needed to keep EXACT
            // time, you would get rid of this.
            if (now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            // Render. To do so, we need to calculate interpolation for a
            // smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
            drawGame(interpolation);
            lastRenderTime = now;

            // Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
               System.out.println("NEW SECOND " + thisSecond + " " + i_frameCount);
               i_currentFPS  = i_frameCount;
               i_frameCount = 0;
               lastSecondTime = thisSecond;
            }

            // Yield until it has been at least the target time between
            // renders. This saves the CPU from hogging.
            while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
               Thread.yield();

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
    * @return the gamePanel
    */
   public GamePanel getGamePanel()
   {
      return gamePanel;
   }

   /**
    * @return the targetFps
    */
   public static double getTargetFps()
   {
      return TARGET_FPS;
   }

   /**
    * @return the gameEngine
    */
   public GameEngine getGameEngine()
   {
      return gameEngine;
   }

   /**
    * @return the i_currentFPS
    */
   public int getI_currentFPS()
   {
      return i_currentFPS;
   }
   
   private void updateLogic()
   {
      gameEngine.update();

   }

   private void drawGame(float interpolation)
   {
      // Doesn't really do anything.
      renderer.getComponent().isVisible();
   }
}
