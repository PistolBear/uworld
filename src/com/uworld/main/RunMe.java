package com.uworld.main;

import javax.swing.JOptionPane;

import com.uworld.gui.UWSwingGui;
import com.uworld.zork.ZorkGameEngine;

/**
 * Starting point for UWorld.
 * 
 * @author woody
 */
public class RunMe
{
   
   static GameInterface ge_engine;
   static UWSwingGui theGui;
   

   public static void main(String[] args)
   {
      if (GameInterface.NON_GRAPHICS_ENFORCED)
      {
         int answer = JOptionPane.showConfirmDialog(null, "Use GUI?");
         System.out.println("Answer chosen for initial dialog (GUI?): " + answer);
   
         if (answer == 2)
         {
            System.exit(answer);
         }
   
         ge_engine = new ZorkGameEngine();
         UWSwingGui.setMode(answer);
         UWSwingGui.prepareGui();
      }
      
      else
      {
         
         
      }
   }

}
