/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Apr 24, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.zork;

import java.util.List;

/**
 * @author PistolBear
 *
 */
public interface IChest
{
   public List peekContents();
   public Treasure removeItem(Treasure t);
   public boolean placeItem(Treasure t);
   public List dumpContents();
   public boolean lock();
   public boolean unlock();

}
