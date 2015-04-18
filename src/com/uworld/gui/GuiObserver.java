/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Nov 28, 2014
 * 
 * Desc:
 * Tags:
 */
package com.uworld.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import com.uworld.main.GameInterface;

/**
 * 
 *  GuiObserver
 *    Implements ActionListener, extends WindowAdapater (Should probably implement the
 *    WindowListener interface instead)
 *    
 *    Description:
 *    Types handled:  
 *     - ActionEvents: From JButtons 
 *     - WindowEvents: WindowClosing
 *     
 *    Types not handled:
 *     - Basically everything else.
 *     
 *    Types need to be handled:
 *     - Keydowns: Enter, Left, Right, Up, Down, Numpad8, 2, 4, and 6, letters i, o, q, w, a, s, d, 
 *       and spacebar.
 *     - Others as they come up. 
 *    
 * @author PistolBear
 *
 */
final class GuiObserver extends WindowAdapter implements ActionListener
{
   private static GameInterface ge_engine; 
   GuiObserver()
   {
      // TODO: set up gui stuff
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource().getClass() == JButton.class)
      {
         switch (e.getActionCommand())
         {
         case "Menu":
            if (togglePaused())
            {
               ((JButton)e.getSource()).setText("Close Menu");
               alertMenu(true);
            }

            else
            {
               ((JButton)e.getSource()).setText("Menu");
               alertMenu(false);
            }
            break;


         case "Pause":
            if (togglePaused())
            {
               ((JButton)e.getSource()).setText("Unpause");
            }
               
            else
            {
               ((JButton)e.getSource()).setText("Pause");
            }
            break;

         case "Quit":
            System.exit(0);
            break;

         default:
         }
      }
      
      

   }

   /**
    * @param b
    */
   private void alertMenu(boolean requestingToOpenMenu)
   {
      if (requestingToOpenMenu)
      {
         
      }
      
      else
      {
         
      }
      
   }

   private boolean togglePaused()
   {
      return ge_engine.togglePaused();
   }

   @Override
   public void windowClosing(WindowEvent e)
   {
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {
         {
            System.exit(0);
         }
      }

   }
}