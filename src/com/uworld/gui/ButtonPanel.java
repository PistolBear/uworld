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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;

/**
 * @author PistolBear
 *
 */
public class ButtonPanel extends JPanel
{
   ButtonPanel(ActionListener listener)
   {
      JButton menuButton = new JButton("Menu");
      menuButton.setActionCommand("Menu");
      menuButton.addActionListener(listener);
      
      JButton pauseButton = new JButton("Pause");
      pauseButton.setActionCommand("Pause");
      pauseButton.addActionListener(listener);
      
      JButton quitButton = new JButton("Quit");
      quitButton.setActionCommand("Quit");
      quitButton.addActionListener(listener);
   }
}
