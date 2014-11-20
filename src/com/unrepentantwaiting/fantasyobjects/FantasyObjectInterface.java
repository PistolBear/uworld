package com.unrepentantwaiting.fantasyobjects;

import com.unrepentantwaiting.main.BaseInteractable;

/**
 * Everything from chests and chairs to goblins heirs can provide this info.
 * 
 * @author woody
 */
public interface FantasyObjectInterface extends BaseInteractable
{
   public boolean isWeapon();

   public boolean isEdible();

   public boolean isWearable();
}
