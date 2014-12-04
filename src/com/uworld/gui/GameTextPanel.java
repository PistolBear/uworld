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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author PistolBear
 *
 */
final class GameTextPanel extends JPanel
{
   protected GameTextPanel(ActionListener listener, Dimension d)
   {
      super(true);
      ActionListener go_observer = listener;
      Dimension parentDimension = d;
      
      
      setBorder(BorderFactory.createTitledBorder("Game Text Panel"));
      setLayout(new GridBagLayout());

      // Setting up the constraints for the JTextArea
      GridBagConstraints textConstraints = new GridBagConstraints();
      textConstraints.gridx = 0;
      textConstraints.gridy = 1;
      textConstraints.gridwidth = 4;
      textConstraints.gridheight = 3;

      // Adding JTextArea to the layout's container with its newly created
      // constraints
      JTextArea textArea = new JTextArea(220, 80); // CTOR(rows, columns)
      add(textArea, textConstraints);

      JTextField textField = new JTextField(80);
      textConstraints.gridy += 1;
      add(textField, textConstraints);




      // Creating constraints for the buttons. Use this for the first button,
      // each new button will be placed in the "next" spot (to the RIGHT,
      // in the standard Left_to_Right orientation.
      GridBagConstraints buttonConstraints = new GridBagConstraints();
      buttonConstraints.gridx = 0;
      buttonConstraints.gridy = textConstraints.gridy + textConstraints.gridheight + 1;
      buttonConstraints.gridwidth = 1;
      buttonConstraints.gridheight = 1;

      
      
      // Make some buttons. Add them to the GuiObserver (listener)
      JButton submit = new JButton("Submit");
      JButton cancel = new JButton("Cancel");
      JButton menu = new JButton("Menu");

      submit.setActionCommand("Submit");
      cancel.setActionCommand("Cancel");
      menu.setActionCommand("Menu");

      submit.addActionListener(go_observer);
      cancel.addActionListener(go_observer);
      menu.addActionListener(go_observer);

      add(submit, buttonConstraints);
      add(cancel);
      add(menu);

      this.setLocation(150, 100);
      this.setPreferredSize(parentDimension);

   }
}
