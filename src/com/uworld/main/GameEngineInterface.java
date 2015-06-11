/******************
 * UWorld File name: Author: PistolBear Created: May 13, 2015 Desc: Tags:
 */
package com.uworld.main;

import com.uworld.gui.UWSwingGui;

/**
 * @author PistolBear
 */
public interface GameEngineInterface extends EngineConstants
{
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

   boolean DEBUG_MODE = true;

   // /// Methods /////
   /**
    * @return
    */
   public UWSwingGui getGui();

   /**
    * updates game engine's internal state in between clusters of user input
    * GameEngineInterface.update is not the same as GUI.update, but may fire
    * GUI.update
    */
   void update(Object ... newStuff);

   boolean togglePaused();

   Menu showMenu();

   /**
    * @return the currentFPS
    */
   int getCurrentFPS();

}
