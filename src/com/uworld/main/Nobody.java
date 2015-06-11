package com.uworld.main;

import java.util.ArrayList;

import com.uworld.actors.IActor;
import com.uworld.fantasyobjects.IInventoryItem;

import faction.Faction;

/**
 * NullClass that allows for testing certain scenarios.
 * 
 * @author PistolBear
 *
 */
public class Nobody implements IBaseInteractable, IActor
{

   @Override
   public int getHealth()
   {
      return 0;
   }

   @Override
   public void directDamage(int d)
   {
   }

   @Override
   public void removeDamage(int d)
   {
   }

   @Override
   public ArrayList<IInventoryItem> getInventoryList()
   {
      return new ArrayList<IInventoryItem>();
   }

   @Override
   public void modSkill(SkillName s, int value)
   {
   }

   @Override
   public void receiveDamage(int d, DamageType damageType)
   {
   }
   
   @Override
   public int getInventorySize()
   {
      return 0;
   }

   @Override
   public int getGold()
   {
      return 0;
   }

   @Override
   public int getArmorValue()
   {
      return 0;
   }

   @Override
   public ArmorType getArmorType()
   {
      return ArmorType.NONE;
   }

   @Override
   public boolean getHostile(IActor a)
   {
      return false;
   }

   @Override
   public void setHostile(IActor a)
   {
   }

   @Override
   public void addInventoryItem(IInventoryItem i)
   {
   }

   @Override
   public void addGold(double g)
   {
   }

   @Override
   public int getInventoryEncumberance()
   {
      return 0;
   }

   @Override
   public void setSkill(SkillName s, int value)
   {
   }

   @Override
   public Faction getFaction()
   {
      return Faction.NONE;
   }
}
