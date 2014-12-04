package com.uworld.fantasyobjects;

import com.uworld.main.DamageType;

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

   /* (non-Javadoc)
    * @see com.uworld.main.Named#getName()
    */
   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.Named#shortDesc()
    */
   @Override
   public String shortDesc()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.Named#longDesc()
    */
   @Override
   public String longDesc()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.BaseInteractable#indirectDamage(int, com.uworld.main.DamageType)
    */
   @Override
   public void indirectDamage(int d, DamageType damageType)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.main.Named#setDescriptionLong(java.lang.String)
    */
   @Override
   public void setDescriptionLong(String s)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.fantasyobjects.FantasyObjectInterface#getEncumberance()
    */
   @Override
   public int getEncumberance()
   {
      // TODO Auto-generated method stub
      return 0;
   }

}
