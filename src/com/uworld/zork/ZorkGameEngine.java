package com.uworld.zork;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Renderer;
import javax.swing.SwingUtilities;

import com.uworld.actors.ActorPC;
import com.uworld.gui.UWSwingGui;
import com.uworld.main.GameEngine;
import com.uworld.main.GameEngineInterface;
import com.uworld.main.Menu;

public class ZorkGameEngine extends GameEngine implements GameEngineInterface
{
   public ZorkGameEngine()
   {
      ge_engine = this;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameEngine#update()
    */
   @Override
   public void update(Object ... newStuff)
   {

   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameEngine#togglePaused()
    */
   @Override
   public boolean togglePaused()
   {
      b_paused = !b_paused;
      return b_paused;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.main.GameEngine#showMenu()
    */
   @Override
   public Menu showMenu()
   {
      // return theGui.showMenuDialog();
      return new Menu() 
      {
         /* (non-Javadoc)
          * @see com.uworld.main.Menu#getMenuTitle()
          */
         @Override
         public String getMenuTitle()
         {
            return "ZorkMenu";
         }
         
      };
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
    * @see com.uworld.main.GameEngine#getI_currentFPS()
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
    * @see com.uworld.main.GameEngine#getGui()
    */
   @Override
   public UWSwingGui getGui()
   {
      return theGui;
   }

   /**
    * @see handleTokens is the primary input to the ZorkGameEngine.
    * TODO: Be more verbose
    */
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
            response = GameEngine.HELP_REQUEST_STRING;
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

   /**
    * @return ZorkGameEngine
    */
   public static ZorkGameEngine getZorkInstance()
   {
      if (ge_engine == null)
         ge_engine = new ZorkGameEngine();
      
      return (ZorkGameEngine) ge_engine;
   }
}
