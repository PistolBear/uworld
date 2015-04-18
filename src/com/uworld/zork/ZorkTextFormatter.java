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
 * ZorkTextFormatter
 * 
 * This class to handle the output for Zork-like interaction.
 * 
 * @author PistolBear
 * 
 */
public class ZorkTextFormatter implements TextOutputMessageIF
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
