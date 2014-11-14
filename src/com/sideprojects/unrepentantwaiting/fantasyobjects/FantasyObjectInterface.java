package com.sideprojects.unrepentantwaiting.fantasyobjects;

import com.sideprojects.unrepentantwaiting.BaseInteractable;

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
