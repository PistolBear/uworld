package com.uworld.actors;

import java.util.List;

import com.uworld.fantasyobjects.IInventoryItem;

public interface ActorPC extends IActor
{
   public long getLevel();

   public int getFacing();

   /**
    * @param s
    */
   public void setFacing(String s);

}
