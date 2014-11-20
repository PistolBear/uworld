package com.unrepentantwaiting.gui;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
   private static LayoutManager layman;

   private static boolean b_usingGui = false;

   private GamePanel()
   {
      super(true);
      layman = new GroupLayout(this);

      GraphicsConfiguration graphics;

      Canvas canvas = new Canvas();

      if (this.isVisible())
      {
         if (getParent() instanceof JFrame)
         {
            JFrame temp = (JFrame) getParent();
            temp.setTitle("Game is running...");
         }
      }
      this.setAlignmentX(0.0f);
      this.setAlignmentY(0.0f);
      this.setPreferredSize(new Dimension(640, 480));

   }

   public GamePanel(boolean gui)
   {
      this();
      b_usingGui = gui;
   }

}
