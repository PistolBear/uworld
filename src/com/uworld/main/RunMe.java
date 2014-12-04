package com.uworld.main;

import javax.swing.JOptionPane;

import com.uworld.gui.UWSwingGui;

/**
 * Starting point for UWorld.
 * 
 * @author woody
 */
public class RunMe
{
   
   static GameEngine ge_engine;
   static UWSwingGui theGui;
   

   public static void main(String[] args)
   {
      int answer = JOptionPane.showConfirmDialog(null, "Use GUI?");
      System.out.println("Answer chosen for initial dialog (GUI?): " + answer);

      if (answer == 2)
      {
         System.exit(answer);
      }

      ge_engine = new GameEngine();
      UWSwingGui.setMode(answer);
      ge_engine.getGui().prepareGui();
   }

}
