package com.sideprojects.unrepentantwaiting;

import java.awt.Point;

import com.sideprojects.unrepentantwaiting.gui.DisplayMode;

/**
 * BaseGrid Presents a construct for defining useable and unuseable spaces in
 * overworlds, taverns, and other scenes. Creates basis for the MapInterface as
 * well.
 * 
 * @author woody
 */
public interface BaseGrid
{
   public int getWidth();

   public int getHeight();

   public int getDepth(); // For respresenting 3D spaces

   public Point p_origin = new Point(0, 0);

   public boolean[][] getFog();

   public void displayGrid(DisplayMode d);

}
