/******************
 * UWorld File name: Author: PistolBear Created: Mar 24, 2015 Desc: Tags:
 */
package com.uworld.zork;

import java.io.IOException;

import javax.swing.SwingUtilities;

/**
 * @author PistolBear
 */
public class ZorkTest
{
   public static void main(String[] args) throws IOException, InterruptedException
   {
      final ZorkWindow z = new ZorkWindow();
      SwingUtilities.invokeLater(new Runnable()
      {

         @Override
         public void run()
         {
            z.createAndShowGUI();

         }

      });

   }

}
