package com.uworld.fantasyobjects;

import com.uworld.main.IBaseInteractable;

/**
 * Everything from chests and chairs to goblins heirs can provide this info.
 * 
 * @author woody
 */
public interface FantasyObjectInterface extends IBaseInteractable
{
   boolean m_inventoryItem = false;
   
   public boolean isWeapon();
   public boolean isEdible();
   public boolean isWearable();
   public int getEncumberance();
}
