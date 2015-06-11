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

/**
 * ZorkWindow<br><br>
 * 
 * This class describes the actual visual components and their behavior.<br>
 * There is nothing beyond view interpretation at this level, everything<br>
 * that is part of the game should be handled by the GameEngine or one<br>
 * of its componenets.<br><br>
 * 
 * This class is a KeyListener to handle the key presses.  Each button added<br>
 * has its own anonymous ActionListener to handle button events.<br><br>
 * 
 * @author PistolBear
 */
@SuppressWarnings("serial")
public class ZorkWindow extends JFrame implements KeyListener
{
   private static ZorkReader reader;
   private static boolean DEBUG_MODE = false;
   JTextField m_inputField;
   JTextArea outputArea;
   
   // Swing buttons on the console-screen.
   private JButton m_buttonA;
   private JButton m_buttonB;
   private JButton m_btnClearAll;
   private JScrollPane scrollPane;
   private ZorkWriter writer;

   static final String NEWLINE = System.getProperty("line.separator");
   private JLabel m_topLabel;

   public ZorkWindow()
   {
      // These drive input and output, respectively.
      reader = new ZorkReader();
      writer = new ZorkWriter();
      
      // You never go back
      setForeground(Color.BLACK);
      
      // Border layout is easy for this simplistic layout
      getContentPane().setLayout(new BorderLayout(0, 0));

      // Components are the pieces of the window (button,
      // TextArea, etc.  See the addComponents() JDoc.
      addComponents();

      // Screw small screens
      setResizable(false);

      // We want people to be able to hit the "X"
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Pack organizes components
      pack();
      
      // Centers screen (must be after pack())
      setLocationRelativeTo(null);
   }

   /**
    * addComponents()
    * 
    * Swing components are initialized here and set to standard
    * labels and positions.  Initially, three buttons, an input
    * TextField and an output TextArea are created, with space
    * for additional things.
    * 
    */
   private void addComponents()
   {
      m_topLabel = new JLabel("UWorld");
      m_topLabel.setHorizontalAlignment(getWidth() / 2);
      getContentPane().add(m_topLabel, BorderLayout.NORTH);

      scrollPane = new JScrollPane();
      getContentPane().add(scrollPane, BorderLayout.CENTER);

      outputArea = new JTextArea();
      outputArea.setBackground(Color.BLACK);
      outputArea.setForeground(Color.WHITE);
      outputArea.setRows(30);
      outputArea.setColumns(1);
      outputArea.setEditable(false);

      scrollPane.setViewportView(outputArea);

      // Panel for input components' bag constraints
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[] { 432 / 3, 432 / 3, 432 / 3 };
      gbl_panel.rowHeights = new int[] { 30, 22, 0 };
      gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
      gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
      
      // Place panel below outputArea
      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.SOUTH);
      panel.setLayout(gbl_panel);

      
      
      // Button constraints object
      GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
      gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
      gbc_btnNewButton.gridx = 0;
      gbc_btnNewButton.gridy = 0;

      // Place first button
      m_buttonA = new JButton("A");
      panel.add(m_buttonA, gbc_btnNewButton);

      // Move next button right one
      gbc_btnNewButton.gridx = 1;
      
      // Place second button
      m_buttonB = new JButton("B");
      panel.add(m_buttonB, gbc_btnNewButton);

      // Clear button is slightly different size, and moved all the
      // way to the right (there are three columns).
      gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
      gbc_btnNewButton.gridx = 2;
      
      // Place last button (clear button).
      m_btnClearAll = new JButton("Clear");
      m_btnClearAll.addActionListener(new ActionListener(){
         /** Handle the clear button clicked event. */
         public void actionPerformed(ActionEvent e)
         {
            // Clear the text components.
            outputArea.setText("");
            m_inputField.setText("");

            // Return the focus to the typing area.
            m_inputField.requestFocusInWindow();
         }
      });
      panel.add(m_btnClearAll, gbc_btnNewButton);

      
      
      // Input field (one line)
      m_inputField = new JTextField();
      m_inputField.addKeyListener(this);
      m_inputField.setColumns(80);
      m_inputField.setFocusCycleRoot(true);
      m_inputField.setFocusTraversalKeysEnabled(false);

      // Input field bag constraints
      GridBagConstraints gbc_textField = new GridBagConstraints();
      gbc_textField.gridwidth = 3;
      gbc_textField.fill = GridBagConstraints.HORIZONTAL;
      gbc_textField.gridx = 0;
      gbc_textField.gridy = 1;
      
      // Place input field
      panel.add(m_inputField, gbc_textField);
   }

   /**
    * Standard display call, can be used if it was hidden<br>
    * for any reason.  Does not re-add components
    * 
    */
   public void createAndShowGUI()
   {
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
      m_inputField.requestFocus();
   }

   /** Handle the key typed event from the text field. */
   public void keyTyped(KeyEvent e)
   {
      if (DEBUG_MODE)
      {
         reader.debugInfo(this, e, "KEY TYPED: ");
      }
   }

   /** Handle the key pressed event from the text field. */
   public void keyPressed(KeyEvent e)
   {
      if (DEBUG_MODE)
      {
         reader.debugInfo(this, e, "KEY PRESSED: ");
      }
   }

   /** Handle the key released event from the text field. */
   public void keyReleased(KeyEvent e)
   {
      String outputStringFromEngine = null;

      if (e.getSource() != m_inputField)
      {
         // We don't care if input happens outside of the inputField
         return;
      }
      
      if (DEBUG_MODE)
      {
         reader.debugInfo(this, e, "KEY RELEASED: ");
      }

      else
      {
         reader.handleKeyPress(this, e);
      }
   }
}
