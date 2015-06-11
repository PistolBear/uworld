/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: May 12, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.logging;

/**
 * @author PistolBear
 *
 */
public abstract class ActionLog
{
   public static void log(String s)
   {
      UWLogger.getInstance().log(LogType.ACTION, s, System.currentTimeMillis());
   }
   
   public static void logObjects(String s, Object ... o)
   {
      if (o == null || o.length == 0)
      {
         log(s);
      }
   }

}
