/*********************************************
 * UWorld File name: 
 * Author: PistolBear 
 * Created: Nov 10, 2014 
 * Desc: 
 * Tags:
 *********************************************/
package com.sideprojects.unrepentantwaiting.gui;

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

   private JLabel mainMenuLabel;
   private JLabel statusLabel;

   private JPanel controlPanel;

   public UWSwingGui()
   {
      prepareGUI();
   }


   public static void main(String[] args)
   {
      UWSwingGui gui = new UWSwingGui();
      gui.showEventDemo();
   }

   private void makeCharacterSheet()
   {
      mainMenu.dispose();
      characterSheet = new JFrame("Character Sheet");
      characterSheet.setSize(mainMenu.getWidth()-1, mainMenu.getHeight()-1);
      characterSheet.setLayout(new GridLayout(3,3));
      
      statusLabel.setText(getCharacterName());
      
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
      characterPanel.setSize(500,500);
      
      characterSheet.add(characterPanel);
      characterSheet.setVisible(true);
   }

   private void prepareGUI()
   {
      mainMenu = new JFrame("Main Menu - UWorld!");
      mainMenu.setSize(400, 400);
      mainMenu.setLayout(new GridLayout(3, 3));
      
      mainMenuLabel = new JLabel("-", JLabel.CENTER);
      statusLabel = new JLabel("-", JLabel.CENTER);

      statusLabel.setSize(350, 100);
      mainMenu.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent windowEvent)
         {
            System.exit(0);
         }
      });
      
      

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainMenu.add(mainMenuLabel);
      mainMenu.add(controlPanel);
      mainMenu.add(statusLabel);
      mainMenu.setVisible(true);
   }


   private void showEventDemo()
   {
      mainMenuLabel.setText("Control in action: Button");
      
      JButton okButton = new JButton("GO");
      JButton submitButton = new JButton("Slow");
      JButton cancelButton = new JButton("Stop");

      okButton.setActionCommand("GO");
      submitButton.setActionCommand("Slow");
      cancelButton.setActionCommand("Stop");

      okButton.addActionListener(new ButtonClickListener());
      submitButton.addActionListener(new ButtonClickListener());
      cancelButton.addActionListener(new ButtonClickListener());

      controlPanel.add(okButton);
      controlPanel.add(submitButton);
      controlPanel.add(cancelButton);

      mainMenu.setVisible(true);
      characterSheet.setVisible(true);
   }
   
   
   private class ButtonClickListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String command = e.getActionCommand();
         if (command.equals("GO"))
         {
            makeCharacterSheet();
//            characterSheet.pack();
            statusLabel.setText("Accelerating...");
            mainMenuLabel.setText("Function:");
         }
         
         else if (command.equals("Slow"))
         {
            statusLabel.setText("Decelerating");
            mainMenuLabel.setText("Function");
         }
         
         else
         {
            statusLabel.setText("Stopped");
            mainMenuLabel.setText("Control in action: Button");
         }
      }
   }

   
   private String getCharacterName()
   {
      // handle to character here
      return "Bob";
   }



}