package com.sideprojects.unrepentantwaiting.gui;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
   private static LayoutManager layman;

   private static boolean b_usingGui = false;

   GamePanel()
   {
      super(true);
      layman = new GroupLayout(this);

      GraphicsConfiguration graphics;

      Canvas canvas = new Canvas();

      this.setAlignmentX(0.0f);
      this.setAlignmentY(0.0f);
      this.setPreferredSize(new Dimension(640, 480));

   }

   GamePanel(boolean gui)
   {
      this();
      b_usingGui = gui;
   }

}
