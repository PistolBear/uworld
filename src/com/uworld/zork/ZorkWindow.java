/******************
 * UWorld File name: Author: PistolBear Created: Mar 24, 2015 Desc: Tags:
 */
package com.uworld.zork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uworld.main.GameInterface;

/**
 * @author PistolBear
 */
@SuppressWarnings("serial")
public class ZorkWindow extends JFrame implements KeyListener, ActionListener
{
   private static ZorkParser reader;

   private static boolean DEBUG_MODE = false;

   private JTextField inputField;

   private JTextArea outputArea;

   private JButton buttonA;

   private JButton buttonB;

   private JButton btnClearAll;

   private JScrollPane scrollPane;

   private ZorkTextFormatter writer;

   static final String NEWLINE = System.getProperty("line.separator");

   public ZorkWindow()
   {
      reader = new ZorkParser();
      writer = new ZorkTextFormatter();

      setForeground(Color.BLACK);
      getContentPane().setLayout(new BorderLayout(0, 0));

      addComponents();

      setResizable(false);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   private void addComponents()
   {
      JLabel lblUworld = new JLabel("UWorld");
      lblUworld.setHorizontalAlignment(getWidth() / 2);
      getContentPane().add(lblUworld, BorderLayout.NORTH);

      scrollPane = new JScrollPane();
      getContentPane().add(scrollPane, BorderLayout.CENTER);

      outputArea = new JTextArea();
      outputArea.setBackground(Color.BLACK);
      outputArea.setForeground(Color.WHITE);
      outputArea.setRows(12);
      outputArea.setColumns(1);
      outputArea.setEditable(false);

      scrollPane.setViewportView(outputArea);

      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.SOUTH);
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[] { 432 / 3, 432 / 3, 432 / 3 };
      gbl_panel.rowHeights = new int[] { 30, 22, 0 };
      gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
      gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      panel.setLayout(gbl_panel);

      buttonA = new JButton("A");
      GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
      gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
      gbc_btnNewButton.gridx = 0;
      gbc_btnNewButton.gridy = 0;
      panel.add(buttonA, gbc_btnNewButton);

      buttonB = new JButton("B");
      GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
      gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
      gbc_btnNewButton_1.gridx = 1;
      gbc_btnNewButton_1.gridy = 0;
      panel.add(buttonB, gbc_btnNewButton_1);

      btnClearAll = new JButton("Clear");
      btnClearAll.addActionListener(this);
      GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
      gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
      gbc_btnNewButton_2.gridx = 2;
      gbc_btnNewButton_2.gridy = 0;
      panel.add(btnClearAll, gbc_btnNewButton_2);

      inputField = new JTextField();
      inputField.addKeyListener(this);
      GridBagConstraints gbc_textField = new GridBagConstraints();
      gbc_textField.gridwidth = 3;
      gbc_textField.fill = GridBagConstraints.HORIZONTAL;
      gbc_textField.gridx = 0;
      gbc_textField.gridy = 1;
      panel.add(inputField, gbc_textField);
      inputField.setColumns(80);
      inputField.setFocusCycleRoot(true);
      inputField.setFocusTraversalKeysEnabled(false);
   }

   /**
    * 
    */
   public void createAndShowGUI()
   {
      pack();
      setVisible(true);
      inputField.requestFocus();
   }

   /**
    * We have to jump through some hoops to avoid trying to print non-printing
    * characters such as Shift. (Not only do they not print, but if you put them
    * in a String, the characters afterward won't show up in the text area.)
    */
   private void displayDebugInfo(KeyEvent e, String keyStatus)
   {

      // You should only rely on the key char if the event
      // is a key typed event.
      int id = e.getID();
      String keyString;
      if (id == KeyEvent.KEY_TYPED)
      {
         char c = e.getKeyChar();
         keyString = "key character = '" + c + "'";
      }
      else
      {
         int keyCode = e.getKeyCode();
         keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
      }

      int modifiersEx = e.getModifiersEx();
      String modString = "extended modifiers = " + modifiersEx;
      String tmpString = KeyEvent.getModifiersExText(modifiersEx);
      if (tmpString.length() > 0)
      {
         modString += " (" + tmpString + ")";
      }
      else
      {
         modString += " (no extended modifiers)";
      }

      String actionString = "action key? ";
      if (e.isActionKey())
      {
         actionString += "YES";
      }
      else
      {
         actionString += "NO";
      }

      String locationString = "key location: ";

      // Standard means number keys on the top row, otherwise they'll be on the
      // numpad (usually).
      int location = e.getKeyLocation();
      if (location == KeyEvent.KEY_LOCATION_STANDARD)
      {
         locationString += "standard";
      }
      else if (location == KeyEvent.KEY_LOCATION_LEFT)
      {
         locationString += "left";
      }
      else if (location == KeyEvent.KEY_LOCATION_RIGHT)
      {
         locationString += "right";
      }
      else if (location == KeyEvent.KEY_LOCATION_NUMPAD)
      {
         locationString += "numpad";
      }
      else
      { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
         locationString += "unknown";
      }

      outputArea.append(keyStatus + NEWLINE + "    " + keyString + NEWLINE + "    " + modString + NEWLINE + "    " + actionString + NEWLINE + "    " + locationString + NEWLINE);
      outputArea.setCaretPosition(outputArea.getDocument().getLength());
   }

   /** Handle the key typed event from the text field. */
   public void keyTyped(KeyEvent e)
   {
      if (DEBUG_MODE)
      {
         displayDebugInfo(e, "KEY TYPED: ");
      }
   }

   /** Handle the key pressed event from the text field. */
   public void keyPressed(KeyEvent e)
   {
      if (DEBUG_MODE)
      {
         displayDebugInfo(e, "KEY PRESSED: ");
      }
   }

   /** Handle the key released event from the text field. */
   public void keyReleased(KeyEvent e)
   {
      String outputStringFromEngine = null;

      if (DEBUG_MODE)
      {
         displayDebugInfo(e, "KEY RELEASED: ");
      }

      else
      {
         if (e.getSource() == inputField)
         {
            if (KeyEvent.VK_ENTER == e.getKeyCode())
            {
               outputStringFromEngine = reader.tokenize(inputField.getText());

               if (outputStringFromEngine.contains(GameInterface.EXIT_COMMAND))
               {
                  outputArea.setText("Exiting...");
                  dispose();
               }

               outputArea.append(NEWLINE + outputStringFromEngine + NEWLINE);
               outputArea.setCaretPosition(outputArea.getDocument().getLength());
               
               inputField.setText("");

            }

            else if (e.isActionKey())
            {
               outputStringFromEngine = reader.tokenize(KeyEvent.getKeyText(e.getKeyCode()));
               outputArea.append(NEWLINE + outputStringFromEngine + NEWLINE);
               outputArea.setCaretPosition(outputArea.getDocument().getLength());

            }
         }
      }
   }

   /** Handle the button click. */
   public void actionPerformed(ActionEvent e)
   {
      // Clear the text components.
      outputArea.setText("");
      inputField.setText("");

      // Return the focus to the typing area.
      inputField.requestFocusInWindow();
   }

}
