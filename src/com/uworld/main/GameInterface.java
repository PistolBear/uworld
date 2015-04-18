/******************
 * UWorld File name: Author: PistolBear Created: Feb 19, 2015 Desc: Tags:
 */
package com.uworld.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.uworld.gui.UWSwingGui;

/**
 * @author PistolBear
 */
public interface GameInterface
{
   // At the very most we will update the game this many times before a new
   // render.
   // If you're worried about visual hitches more than perfect timing, set this
   // to 1.
   int MAX_UPDATES_BEFORE_RENDER = 3;

   long serialVersionUID = 5556579242006622965L;

   double GAME_HERTZ = 30.0;

   double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ; // Calculate ns each
                                                          // frame should take
                                                          // per hertz.

   double TARGET_FPS = 60;

   boolean NON_GRAPHICS_ENFORCED = false;

   List<String> MESSAGE_POOL = new LinkedList<String>();

   String EXIT_COMMAND = "EXIT_COMMAND_ACCEPTED";

   String HELP_REQUESTED_ZORK = 
              "*** HELP SCREEN ***\n Common commands:\n - exit: exits program" 
            + "\n - North/South/East/West: turn that direction, or go that way if you're facing it"
            + "\n - Arrow Up/Down: move forward, backward\n - Arrow Left/Right: turn left or right"
            + "\n - look/see/search <target>: investigate the target. No target = surroundings"
            + "\n - use/throw/eat <item> <target>: perform the action with the object you have upon"
            + " the target you specified.";

   String FORWARD_REQUEST = "FORWARD_ACTION_REQUESTED";
   String BACKWARD_REQUEST = "BACKWARD_ACTION_REQUESTED";

   void update();

   boolean togglePaused();
   
   Object showMenu();

   /**
    * @return the currentFPS
    */
   int getCurrentFPS();

   /**
    * run() Probably doing this wrong.
    */
   void run();

   /**
    * @return
    */
   UWSwingGui getGui();

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
    * @param tokens
    * @return
    */
   String handleTokens(ArrayList<String> tokens);

}