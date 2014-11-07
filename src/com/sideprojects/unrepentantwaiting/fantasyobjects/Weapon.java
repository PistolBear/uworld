package com.sideprojects.unrepentantwaiting.fantasyobjects;

import com.sideprojects.unrepentantwaiting.Named;

public class Weapon extends Named implements InventoryItemInterface
{
   protected boolean b_melee;

   protected boolean b_loaded;

   protected boolean b_verbose;

   protected boolean b_magic;

   protected double d_weight;

   protected int i_damage;

   protected int i_health_max;

   protected int i_health;

   protected int i_hardness;

   protected int i_range;

   protected WeaponType wt_type;

   protected boolean b_lootable;

   public enum WeaponType {
      DUAL, // DUAL items can be ranged OR melee. Primary use is indicated by
            // b_melee
      SIMPLE, MARTIAL, EXOTIC, MONK, WAND;
   }

   /**
    * Sets default values.
    */
   public Weapon()
   {
      b_melee = true;
      b_loaded = false;
      b_verbose = false;
      b_magic = false;
      d_weight = 1.0;
      i_damage = 1;
      i_health_max = 5;
      i_health = 5;
      i_hardness = 2;
      i_range = 0;

      wt_type = WeaponType.SIMPLE;
      b_lootable = true;
   }

   /**
    * Some quick defaults for unusual weapons, like wands and dual weapons. A
    * dual weapon's damage is determined by the same i_damage field, but the
    * heads of the weapon will have a special modifier that is applied to it.
    * For instance, a gnomish hammerpick may have damage on the heavier hammer
    * side be
    * 
    * @param w
    */
   public Weapon(WeaponType w)
   {
      this();
      wt_type = w;

      if (w == WeaponType.DUAL)
      {

         i_health_max = i_health = 10;
         i_hardness = 1;
      }

      if (w == WeaponType.WAND)
      {
         b_magic = true;
         i_hardness = 1;
         i_health_max = 2;
         i_health = 2;
         i_range = 50;
      }

   }

   /**
    * Non-standard items use this to alter if they can be used as an impromptu
    * weapon. For instance, chairs would have this be true, balls of cotton
    * would be false.
    */
   @Override
   public boolean isWeapon()
   {
      return true;
   }

   /**
    * Edible weapons should be context-sensitive to the story, and won't use
    * this.
    */
   @Override
   public boolean isEdible()
   {
      return false;
   }

   /**
    * Weapons are generally not wearable. However, this can be overridden again
    * in a special case weapon class.
    */
   @Override
   public boolean isWearable()
   {
      return false;
   }

   /**
    * Weapons have a standard health of 5 hp.
    */
   @Override
   public int getHealth()
   {
      return i_health;
   }

   /**
    * Apply damage to the weapon. Deduct hardness from the damage. Damage to
    * objects can be 0.
    */
   @Override
   public void directDamage(int d)
   {
      if (d - i_hardness > 0)
      {
         i_health = i_health - (d + i_hardness);
         if (b_verbose)
         {
            System.out.println((d - i_hardness) + " damage done to " + s_name);
         }
      }
   }

   /**
    * Apply damage to weapon, ignoring hardness.
    */
   @Override
   public void indirectDamage(int d)
   {
      i_health = i_health - d;

   }

   /**
    * Repairing an item cannot go over its max
    */
   @Override
   public void removeDamage(int d)
   {
      if (i_health == i_health_max)
         return;

      i_health += 1;
      removeDamage(d - 1);
   }

   @Override
   public void setName(String s)
   {
      if (s == null)
         s = "A weapon";
      s_name = s;
   }

   @Override
   public void setDescription(String s)
   {
      if (s == null)
         s = "Used as a weapon";
      s_descriptionShort = s;

   }

   public int getPower()
   {
      return i_damage;
   }

   public int getRange()
   {
      return i_range;
   }

}
