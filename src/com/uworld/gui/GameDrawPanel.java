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

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;

import javax.swing.JPanel;

/**
 * GameDrawPanel
 * 
 *   Currently marked as Deprecated as it has not been fully implemented yet.  
 *   Need to add graphical interface here.
 * 
 * @author PistolBear
 *
 */
@Deprecated 
final class GameDrawPanel extends JPanel
{
   protected GameDrawPanel(Dimension d)
   {
      super(true);

      Dimension parentDimension = d;
      // Need these for any self respecting graphical context:
      GraphicsConfiguration graphics;
      Canvas canvas = new Canvas();

      this.setPreferredSize(parentDimension);
      this.setMinimumSize(new Dimension((int) parentDimension.getWidth() - 10, (int) parentDimension.getHeight() - 15));
      this.setMaximumSize(new Dimension((int) parentDimension.getWidth() + 10, (int) parentDimension.getHeight() + 15));
   }

   GameDrawPanel updateGameDrawPanel()
   {
      // TODO: change to do fancy graphics things
      return this;
   }
}