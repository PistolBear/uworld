/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Jan 29, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.zork;

import java.io.Console;

/**
 * ZorkWriter
 * 
 * This class to handle the output for Zork-like interaction.
 * 
 * @author PistolBear
 * 
 */
public class ZorkWriter implements TextOutputMessageIF
{
   Boolean f;

   @Override
   public boolean print(Object... args)
   {
      if (args == null)
      {
         return false;
      }
      
      return true;
   }

}
