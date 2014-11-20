/*********************************************
 * UWorld File name: Author: PistolBear Created: Nov 10, 2014 Desc: Tags:
 *********************************************/
package com.unrepentantwaiting.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * @author PistolBear
 */
public class UWSwingGui extends JWindow
{
   private static final long serialVersionUID = -1599100129329982126L;

   private JFrame mainMenu;
   private JFrame characterSheet;

   private TextInputDialog textInputDialog;

   // infoLabel gives important info about current actions.
   private JLabel infoLabel;
   private JLabel statusLabel;
   
   private JPanel controlPanel;

   public UWSwingGui()
   {
      prepareGUI();
   }

   
   /**
    * A handful of useful helper classes for quick dialogs. 
    * AskAQuestion
    * 
    */
   public boolean AskABoolean(String s)
   {
      if (textInputDialog == null)
      {
         textInputDialog = new TextInputDialog();
      }
      return textInputDialog.askBool(s, true);
   }

   

   
   /**
    * TextInputDialog
    * 
    * A dialog box for asking questions that might require text input.
    * 
    * Can return a boolean with askBool or an Object with askObject
    * @author PistolBear
    *
    */
   private class TextInputDialog
   {
      final JFrame parent = new JFrame();

      JButton button = new JButton();

      String questionString = "This is a question?";
      boolean yesno = false;

      @SuppressWarnings("unused")
      public boolean askBool(String s)
      {
         questionString = s;
         return askBool();
      }
      
      public boolean askBool(String s, boolean b)
      {
         yesno = b;
         questionString = s;
         return askBool();
      }

      public boolean askBool()
      {
         button.setText(questionString);
         parent.add(button);

         button.addActionListener(new java.awt.event.ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               String name = JOptionPane.showInputDialog(parent, "Yes or no question?", null);
            }
         });
         
         pack();
         setVisible(true);
         
         return yesno;
      }
   }

   private void makeCharacterSheet()
   {
      mainMenu.dispose();
      characterSheet = new JFrame("Character Sheet");
      characterSheet.setSize(mainMenu.getWidth() - 1, mainMenu.getHeight() - 1);
      characterSheet.setLayout(new GridLayout(3, 3));

      
//      statusLabel.setText(getCharacterName());

      this.setIconImage(null);

      characterSheet.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent windowEvent)
         {
            mainMenu.pack();
         }
      });

      JPanel characterPanel = new JPanel(true);
      characterPanel.setLayout(new GridLayout(5, 5));
      characterPanel.setName("Character Panel");
      characterPanel.setBackground(Color.red);
      characterPanel.setForeground(Color.black);
      characterPanel.setSize(500, 500);

      characterSheet.add(characterPanel);
      characterSheet.setVisible(true);
   }

   private void prepareGUI()
   {
      textInputDialog = new TextInputDialog();
      
      statusLabel = new JLabel("-", JLabel.CENTER);
      statusLabel.setSize(350, 100);

      mainMenu = new JFrame("Main Menu - UWorld!");
      infoLabel = new JLabel("-", JLabel.CENTER);
      mainMenu.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent windowEvent)
         {
            System.exit(0);
         }
      });

      controlPanel = new JPanel();
      controlPanel.setPreferredSize(new Dimension(400, 400));
      controlPanel.setMaximumSize(new Dimension (500, 500));
      controlPanel.setMinimumSize(new Dimension(100, 100));
      controlPanel.setLayout(new GridLayout());
      

      mainMenu.setLayout(controlPanel.getLayout());
      mainMenu.add(infoLabel);
      mainMenu.add(controlPanel);
      mainMenu.add(statusLabel);
      
      mainMenu.setVisible(true);
      mainMenu.setFocusable(true);
   }

   private void showEventDemo()
   {
      infoLabel.setText("Control in action: Button");

      JButton okButton = new JButton("GO");
      JButton submitButton = new JButton("Slow");
      JButton cancelButton = new JButton("Stop");

      okButton.setActionCommand("GO");
      submitButton.setActionCommand("Slow");
      cancelButton.setActionCommand("Stop");

      okButton.addActionListener(new UWMasterButtonListener());
      submitButton.addActionListener(new UWMasterButtonListener());
      cancelButton.addActionListener(new UWMasterButtonListener());

      controlPanel.add(okButton);
      controlPanel.add(submitButton);
      controlPanel.add(cancelButton);

      mainMenu.pack();
      mainMenu.setVisible(true);
//      makeCharacterSheet();
      
//      characterSheet.setVisible(true);
   }

   
   
   private class UWMasterButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String command = e.getActionCommand();
         if (command.equals("GO"))
         {
            makeCharacterSheet();
            // characterSheet.pack();
            statusLabel.setText("Accelerating...");
            infoLabel.setText("Function:");
         }

         else if (command.equals("Slow"))
         {
            statusLabel.setText("Decelerating");
            infoLabel.setText("Function");
         }

         else if (command.equals("Stop"))
         {
            statusLabel.setText("Stopped");
            infoLabel.setText("Control in action: Button");
         }
         
         else
         {
            statusLabel.setText(" ");
            infoLabel.setText(" ");
         }
      }
   }
}