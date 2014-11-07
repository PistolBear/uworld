package com.sideprojects.unrepentantwaiting.fantasyobjects;

/**
 * DualWeapons have a few special things special about them, due to them having
 * two heads (and thus two modes of damage dealing, even if the weapon's heads
 * do the same amount of damage).
 * 
 * @author PistolBear
 */
public class DualWeapon extends Weapon
{
   int i_secondaryDamage = 0;

   DualWeapon()
   {
      super(WeaponType.DUAL);
      i_secondaryDamage = i_damage;
   }

}
