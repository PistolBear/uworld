package com.uworld.actors;

import java.util.ArrayList;
import java.util.List;

import com.uworld.fantasyobjects.IInventoryItem;
import com.uworld.logging.ActionLog;
import com.uworld.main.Armor;
import com.uworld.main.ArmorType;
import com.uworld.main.DamageType;
import com.uworld.main.EngineConstants;

import faction.Faction;

/**
 * This is the standard Actor class that defines the base PC,<br>
 * along with some NPCs which want to inherit certain PC<br> 
 * traits (TBD).
 * 
 * An ActorPC requires definition upon creation of a few methods.<br>
 * 
 * 
 * @author PistolBear
 *
 */
public abstract class ActorPC implements IActor
{
   private static final int DEATH_VALUE = -10;
   protected int i_level = 1;
   protected int i_facing = EngineConstants.NORTH;
   protected int i_health = 100;
   private List<DamageType> Weaknesses;
   private List<DamageType> Resistances;
   private Armor a_armor;
   
   protected void init()
   {
      Weaknesses = new ArrayList<DamageType>();
      Resistances = new ArrayList<DamageType>();
      
   }
   
   
   /**
    * DamageType is used for knowing what killed the PC.
    * Override this method with specific actions for the PC
    * to follow, especially in Zork.
    */
   abstract protected void die(DamageType t);
   
   
   public long getLevel()
   {
      return i_level;
   }

   public int getFacing()
   {
      return 0;
   }

   public void setFacing(String s)
   {
      // check stuff
      switch (s)
      {
         case "N":
         case "NORTH":
            i_facing = EngineConstants.NORTH;
            break;
         case "S":
         case "SOUTH":
            i_facing = EngineConstants.SOUTH;
            break;
         case "E":
         case "EAST":
            i_facing = EngineConstants.EAST;
            break;
         case "W":
         case "WEST":
            i_facing = EngineConstants.WEST;
            break;
         default:
            i_facing = Integer.parseInt(s);
      }
   }

   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#getHealth()
    */
   @Override
   public int getHealth()
   {
      return i_health;
   }

   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#directDamage(int)
    */
   @Override
   public void directDamage(int d)
   {
      if (EngineConstants.VERBOSE)
      {
         System.out.println("Unmodified: ActorPC.directDamage(" + d + ")");
      }
      
      i_health -= d;
      if (i_health <= DEATH_VALUE)
      {
         die(DamageType.DIRECT);
      }
   }

   /* 
    * @see com.uworld.main.IBaseInteractable#indirectDamage(int, com.uworld.main.DamageType)
    */
   @Override
   public void receiveDamage(int d, DamageType damageType)
   {
      float multiplier = 1f;
      for (DamageType t : Weaknesses)
      {
         if (t.equals(damageType))
         {
            multiplier *= 1.5f;
         }
      }
      
      for (DamageType t : Resistances)
      {
         if (t.equals(damageType))
         {
            multiplier *= 0.75f;
         }
      }
      
      int damageReceived = (int)((float) d * multiplier);
      if (damageReceived <= 0)
      {
         damageReceived = 1;
      }
      
      ActionLog.log("Received " + damageReceived + " damage (" + damageType + ")!");
      
      i_health -= d;
      
      if (i_health <= DEATH_VALUE)
      {
         die(damageType);
      }
   }

   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#removeDamage(int)
    */
   @Override
   public void removeDamage(int d)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getArmorValue()
    */
   @Override
   public int getArmorValue()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getArmorType()
    */
   @Override
   public ArmorType getArmorType()
   {
      
      return a_armor.getType();
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getInventoryList()
    */
   @Override
   public List getInventoryList()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getInventorySize()
    */
   @Override
   public int getInventorySize()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getInventoryEncumberance()
    */
   @Override
   public int getInventoryEncumberance()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getGold()
    */
   @Override
   public int getGold()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getHostile(com.uworld.actors.IActor)
    */
   @Override
   public boolean getHostile(IActor a)
   {
      // TODO Auto-generated method stub
      return false;
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#setHostile(com.uworld.actors.IActor)
    */
   @Override
   public void setHostile(IActor a)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#addInventoryItem(com.uworld.fantasyobjects.IInventoryItem)
    */
   @Override
   public void addInventoryItem(IInventoryItem i)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#addGold(double)
    */
   @Override
   public void addGold(double g)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#setSkill(com.uworld.actors.IActor.SkillName, int)
    */
   @Override
   public void setSkill(SkillName s, int value)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#modSkill(com.uworld.actors.IActor.SkillName, int)
    */
   @Override
   public void modSkill(SkillName s, int value)
   {
      // TODO Auto-generated method stub
      
   }

   /* (non-Javadoc)
    * @see com.uworld.actors.IActor#getFaction()
    */
   @Override
   public Faction getFaction()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
