/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: May 6, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.actors;

import sun.util.logging.resources.logging;

import com.uworld.main.DamageType;
import com.uworld.logging.*;

/**
 * @author PistolBear
 *
 */
public final class ActorFactory
{
   private ActorFactory() {}

   public static ActorPC makePC()
   {
      ActorPC actor = new ActorPC()
      {

         @Override
         protected void die(DamageType t)
         {
            
         }
         
      };
      return actor;
   }
}
