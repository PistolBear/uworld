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
    * @see com.uworld.main.NamedInteractable#getName()
    */
   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#shortDesc()
    */
   @Override
   public String getShortDesc()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#longDesc()
    */
   @Override
   public String getLongDesc()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#indirectDamage(int, com.uworld.main.DamageType)
    */
   @Override
   public void receiveDamage(int d, DamageType damageType)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#setDescriptionLong(java.lang.String)
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