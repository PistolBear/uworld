package com.uworld.fantasyobjects;

import com.uworld.main.BaseInteractable;

/**
 * Everything from chests and chairs to goblins heirs can provide this info.
 * 
 * @author woody
 */
public interface FantasyObjectInterface extends BaseInteractable
{
   boolean m_inventoryItem = false;
   
   public boolean isWeapon();
   public boolean isEdible();
   public boolean isWearable();
   public int getEncumberance();
}
