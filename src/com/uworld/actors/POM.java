/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Dec 3, 2014
 * 
 * Desc:
 * Tags:
 */
package com.uworld.actors;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import com.uworld.fantasyobjects.InventoryItemInterface;
import com.uworld.main.DamageType;
import com.uworld.main.Named;
import com.uworld.actors.DialogueInterface;

/**
 * @author PistolBear
 *
 */
public abstract class POM implements ActorNPC, ActorInterface, Named
{
   private int i_health;
   private int i_armor;
   private String s_name;
   private int i_healthMax;
   private String s_armorType;
   private List<InventoryItemInterface> l_inventoryList;
   private int i_gold;
   private List<ActorInterface> l_explicitEnemies;
   private List<Skill> l_skills;
   private DialogueInterface di_dialog;
   private String s_description;
   private String s_longDescription;
   private boolean b_lootable;
   private List<Faction> l_faction;

   @Override
   public int getHealth()
   {
      return i_health;
   }

   @Override
   public void directDamage(int d)
   {
      i_health -= d;
      
      if(i_health <= 0)
      {
         System.out.println(s_name + " died.");
      }
   }

   @Override
   public void indirectDamage(int d, DamageType damageType)
   {
      if (damageType.getResistances() != null && damageType.getResistances().contains(s_armorType))
      {
         i_health -= (i_armor + (d-i_armor > 0 ? d-i_armor : d));
      }
   }

   @Override
   public void removeDamage(int d)
   {
      i_health += (d + i_health > i_healthMax ? i_healthMax : d + i_health); 
   }

   @Override
   public int getArmorValue()
   {
      return i_armor;
   }

   @Override
   public String getArmorType()
   {
      return s_armorType;
   }

   @Override
   public List<InventoryItemInterface> getInventoryList()
   {
      return l_inventoryList;
   }

   @Override
   public int getInventorySize()
   {
      return l_inventoryList.size();
   }

   @Override
   public int getInventoryEncumberance()
   {
      int total = 0;
      for (InventoryItemInterface i : l_inventoryList)
      {
         total += i.getEncumberance();
      }
      
      return total;
   }

   @Override
   public int getGold()
   {
      return i_gold;
   }

   @Override
   public boolean getHostile(ActorInterface a)
   {
      List<Faction> enemies = a.getFaction().getCommonEnemies();
      for (Faction each : enemies)
      {
         if (this.getFaction() == each)
            return true;
      }
      
      // Do another loop for getExplicitEnemies, etc.
      
      return false;
   }

   @Override
   public void setHostile(ActorInterface a)
   {
      l_explicitEnemies.add(a);
   }

   @Override
   public void addInventoryItem(InventoryItemInterface i)
   {
      l_inventoryList.add(i);
   }

   @Override
   public void addGold(double g)
   {
      i_gold += g;
   }

   @Override
   public void modSkill(SkillName s, int value)
   {
   }

   @Override
   public void setName(String s)
   {
      if (s == null || s.length() == 0 || s.contains(":;\\\b\t1234567890"))
         return;
      
      s_name = s;
   }

   @Override
   public void setDescription(String s)
   {
      if (s == null || s.length() == 0 || s.contains(":;\\\b\t1234567890"))
         return;
      
      s_description = s;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.Named#setDescriptionLong()
    */
   @Override
   public void setDescriptionLong(String s)
   {
      if (s == null || s.length() == 0 || s.contains(":;\\\b\t1234567890"))
         return;
      
      s_longDescription = s;
   }

   @Override
   public String getName()
   {
      if (s_name == null)
         return "Unnamed";
      
      return s_name;
   }

   @Override
   public String shortDesc()
   {
      return s_description;
   }

   @Override
   public String longDesc()
   {
      return s_longDescription;
   }

   @Override
   public boolean getLootable()
   {
      return b_lootable;
   }

   @Override
   public boolean hasDialogue()
   {
      return di_dialog.exists();
   }

   @Override
   public void addDialogue(DialogueInterface di)
   {
      di_dialog.append(di);
   }

   @Override
   public void setSkill(SkillName s, int value)
   {
      int skillNdx = l_skills.indexOf(s);
      if(skillNdx > -1)
      {
         l_skills.get(skillNdx).setValue(value);
      }
   }

   @Override
   public Faction getFaction()
   {
      Collections.sort(l_faction);
      return l_faction.get(0);
   }

}
