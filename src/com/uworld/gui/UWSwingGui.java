/*********************************************
 * UWorld File name: Author: PistolBear Created: Nov 10, 2014 Desc: Tags:
 *********************************************/
package com.uworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.uworld.main.GameInterface.*;

/**
 * UWSwingGui class
 * 
 *   The Swing class that handles all Swing-based action for the game, including initial game entry,
 *   text-based and simple-graphics modes.  May be replaced for the Android version.
 *   
 *   
 * @author PistolBear
 */
public final class UWSwingGui extends JFrame
{
   private static final long serialVersionUID = -1599100129329982126L;
   private JPanel topPanel;
   private JPanel bottomPanel;
   private static boolean b_usingGui = false;
   private static boolean b_menuShowing = false;
   // private static ZorkGameEngine ge_engine;
   private final GuiObserver go_observer;
   private static final Dimension d_panelDimension = new Dimension(800, 600);
   private static final JLabel s_statusLabel = new JLabel("", JLabel.CENTER);
   private static UWSwingGui guiFrame = null;
   private static boolean b_modeHasBeenSet = false;
   

   
   
   /**
    *  Constructor for UWSwingGui
    *  
    *    If this has been run before, does not override the new state.
    *    
    *  @param usingGui
    */
   public UWSwingGui()
   {
      go_observer = new GuiObserver();
      
   }

   
   /**
    * prepareGUI()
    * 
    *   Initializes all the parts of the main GUI structure being used.  
    * 
    * @return
    */
   @SuppressWarnings("deprecation")
   public static UWSwingGui prepareGui()
   {
      if (guiFrame == null)
      {
         guiFrame = new UWSwingGui();
      }
      
      if (!b_modeHasBeenSet)
      {
         setMode(1);
      }
      
      guiFrame.setLayout(new BorderLayout());

      if (b_usingGui)
      {
         guiFrame.bottomPanel = new GameDrawPanel(d_panelDimension);
      }
      
      else
      {
         guiFrame.bottomPanel = new GameTextPanel(guiFrame.go_observer, d_panelDimension);
      }
      
      guiFrame.setAlwaysOnTop(true);

      s_statusLabel.setText("Status");
      s_statusLabel.setSize(350, 100);
      s_statusLabel.setToolTipText("This is what screen you're on!");
      s_statusLabel.setBackground(Color.BLACK);
      s_statusLabel.setForeground(Color.WHITE);
      s_statusLabel.setSize(400, 40);
      guiFrame.add(s_statusLabel, BorderLayout.NORTH);
      
      // initializeTopPanel()  // For now, just write out the code to do this.
      
      guiFrame.topPanel = new ButtonPanel(guiFrame.go_observer);
      
      

      // The frame will have two different panels inside it, with 
      // different LayoutManagers for each.  The topPanel will have
      // FlowLayout and the bottom will vary depending on the
      // setting of b_usingGui.
      guiFrame.getContentPane().add(guiFrame.topPanel, BorderLayout.NORTH);
      guiFrame.getContentPane().add(guiFrame.bottomPanel, BorderLayout.SOUTH);

      guiFrame.addWindowListener((WindowListener) guiFrame.go_observer);
      guiFrame.getContentPane().setBackground(Color.black);
      guiFrame.setSize(400,400);
      guiFrame.setLocation(200,100);
      guiFrame.setVisible(true);
      guiFrame.setFocusable(true);
      
      
      return guiFrame;
   }

   
   
   /**
    * A handful of useful helper classes for quick dialogs. AskAQuestion
    */

   /***************************************************************************/
   /***************************************************************************/
   /***************************************************************************/
   /***************************************************************************/
   /***************************************************************************/
   /***************************************************************************/
   /***************************************************************************/

   /**********************
    * Inner Classes      *
    *   GuiObserver      *
    *                    *
    *                    *
    * @author PistolBear *
    **********************/

   /***************************************************************************/
   
   
   /***************************************************************************
    * Helper methods
    *  getActionListener() - used to return the Listener for all of the Swing components.
    * 
    *  
    **************************************************************************/
   
   
   
   /**
    * @return GuiObserver
    */
   public ActionListener getActionListener()
   {
      return go_observer;
   }

   public UWSwingGui updateGui()
   {
      SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>()
      {

         @Override
         protected Boolean doInBackground() throws Exception
         {

            for (int i = 0; i < 30; i++)
            {
               Thread.sleep(100);
               System.out.println("Hello, I've run through my doInBackground: " + i + " times.");

               publish(i);
            }

            return true;
         }

         @Override
         protected void process(List<Integer> chunks)
         {
            int value = chunks.get(chunks.size() - 1);

            s_statusLabel.setText("Current value: " + value);
         }

         @Override
         protected void done()
         {
            Boolean statusDone = false;
            try
            {
               statusDone = get();
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
            catch (ExecutionException e)
            {
               e.printStackTrace();
            }
            if (!statusDone)
            System.out.println("Update GUI if  you want here");
         }
      };

      worker.execute();
      
      return this;
   }


   /**
    * setMode
    * UWSwingGui setMode receives an int returned from the JOptionPane.showConfirmDialog method.
    * 0 = Yes    (b_usingGui == true)
    * 1 = No     (b_usingGui == false)
    * 2 = Cancel (b_usingGui == false)
    * 
    * On a 2, this method should not even get called, because the program should exit.
    * 
    * @param answer
    */
   public static void setMode(int answer)
   {
      b_modeHasBeenSet  = true;
      b_usingGui = (answer == 0 ? true : false);
      
      if (guiFrame == null)
      {
         guiFrame = prepareGui();
      }
   }


   /**
    * showMenuDialog Initial menu dialog. This is a top-level menu for getting
    * to the following submenues: Load menu - load game Save menu - save game
    * Journal menu - show the Journal (game implementation dependent)
    * 
    * @return
    */
   public int showMenuDialog()
   {
      b_menuShowing = true;
      // Not fully implemented. Just put these values for testing.

      // TODO: Fix this.
      JOptionPane pane = new JOptionPane();
      pane.setSize(200, 100);
      pane.setFocusable(true);
      pane.setToolTipText("Choose something!");
      int result = JOptionPane.showOptionDialog(null, "Options", "Menu Screen", 0, 
               JOptionPane.INFORMATION_MESSAGE, null, TopLevelMenuOptions.values(), null);
      switch (result)
      {
         default:
            break;
      }
      
      b_menuShowing = false;
      return result;
   }

}