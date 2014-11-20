package com.unrepentantwaiting.main;

import java.util.ArrayList;

import com.unrepentantwaiting.actors.ActorInterface;
import com.unrepentantwaiting.fantasyobjects.InventoryItemInterface;

public class Nobody implements BaseInteractable, ActorInterface
{

   @Override
   public int getHealth()
   {
      return 0;
   }

   @Override
   public void directDamage(int d)
   {
      // No-op
   }

   @Override
   public void indirectDamage(int d)
   {
      // No-op

   }

   @Override
   public void removeDamage(int d)
   {
      // No-op

   }

   @Override
   public ArrayList<InventoryItemInterface> getInventoryList()
   {
      return null;
   }

   @Override
   public int getInventorySize()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getGold()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getArmorValue()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public String getArmorType()
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean getHostile(ActorInterface a)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void setHostile(ActorInterface a)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void addInventoryItem(InventoryItemInterface i)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void addGold(double g)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public int getInventoryEncumberance()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public void setSkill(SkillName s, int value)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void modSkill(SkillName s, int value)
   {
      // TODO Auto-generated method stub

   }

}
