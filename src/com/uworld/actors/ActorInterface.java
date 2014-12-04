package com.uworld.actors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.uworld.fantasyobjects.InventoryItemInterface;
import com.uworld.main.BaseInteractable;

public interface ActorInterface extends BaseInteractable
{
   public int getArmorValue();
   public String getArmorType();
   @SuppressWarnings("rawtypes")
   public List getInventoryList();
   public int getInventorySize();
   public int getInventoryEncumberance();
   public int getGold();
   public boolean getHostile(ActorInterface a);
   public void setHostile(ActorInterface a);
   public void addInventoryItem(InventoryItemInterface i);
   public void addGold(double g);
   public void setSkill(SkillName s, int value);
   public void modSkill(SkillName s, int value);
   public Faction getFaction();

   
   
   public enum SkillName {
      MELEE_ATTACK, RANGED_ATTACK, MAGIC_ATTACK, MAGIC_EFFECT, USE_ROPE, ACROBATICS, PROFESSION, DIPLOMACY, BLUFF, SENSE_MOTIVE, PICK_LOCK, SET_TRAP, PERCEIVE_SOUND, PERCEIVE_LIGHT, PERCEIVE_SENT, CRAFT_ITEM, TRIP, HIDE, TRACK, GUESS, REMEMBER, HAUL, PUSH_SELF, CALM_OTHER, BEAST_HANDLING, TRAIN_OTHER, TRAIN_ANIMAL, BARTER, CHEAT, CONCEAL, AVOID_PANIC, ENDURE, CLOT, LUCK;
   }

   public enum SkillEnhancement {
      USEABLE, UNUSEABLE, COMBAT, NONCOMBAT, MAGICAL, ANTIMAGICAL, USES_ADDITIONAL_SKILL, REPLACES_SKILL, CRITICAL, LIMITED_USE, FROM_RACE, FROM_CLASS, FROM_EQUIPMENT, TEMPORARY, IGNORES_ATTRIBUTE, IGNORES_ARMOR, IGNORES_LANGUAGE, POWERFUL, VERY_POWERFUL, EPIC, WEAK, VERY_WEAK, INEFFECTIVE, EVIL_TOUCHED, GOOD_TOUCHED, CHAOTIC_TOUCHED, LAWFUL_TOUCHED, VAMPIRIC, FIRE_TOUCHED, ICE_TOUCHED, LIGHTNING_TOUCHED, ACID_TOUCHED, SOUND_TOUCHED, AIR_TOUCHED, EARTH_TOUCHED, LIGHT_TOUCHED, DARK_TOUCHED, WATER_TOUCHED, PLANT_TOUCHED, FEY_TOUCHED, UNWORLDLY, ETHEREAL, CELESTIAL, INFERNAL, ABYSSAL, UNREAL, EXTRA_REAL, STORY_MAIN, STORY_SIDE, SLASHING, BLUDGEONING, PIERCING, STOPPING, MOVING, SLICK, STICKY, STRANGE;
   }

   public enum AbilityName {
      CON, AGI, KNO, WIL, NON;

      public AbilityNameExtended longAbilityName(AbilityName n)
      {
         switch (n)
         {
            case CON:
               return AbilityNameExtended.CONSTITUTION;
            case AGI:
               return AbilityNameExtended.AGILITY;
            case KNO:
               return AbilityNameExtended.KNOWLEDGE;
            case WIL:
               return AbilityNameExtended.WILLPOWER;
            default:
               return AbilityNameExtended.NONE;
         }
      }
   }

   public enum AbilityNameExtended 
   {
      CONSTITUTION, AGILITY, KNOWLEDGE, WILLPOWER, NONE;

      public AbilityName shortAbilityName(AbilityNameExtended n)
      {
         switch (n)
         {
            case CONSTITUTION:
               return AbilityName.CON;
            case AGILITY:
               return AbilityName.AGI;
            case KNOWLEDGE:
               return AbilityName.KNO;
            case WILLPOWER:
               return AbilityName.WIL;
            default:
               return AbilityName.NON;
         }
      }
   }
   
   public enum Faction
   {
      COMMONER, MILITARY, TRAINER, CLERGY_GOOD, CLERGY_EVIL, NOBLE, HIGH_COMMONER, SLUM, 
      REDBEAK, PIRATE, NORTHERNER, SOUTHERNER, WESTERNER, EASTERNER, MINIQUEST, CELESTIAL,
      INFERNAL, BANDIT, ERUDITE, ABNEGATE, ARCANA, STRANDS_OF_THE_EAST, HALFBLOOD, FULLBLOOD,
      SPECIAL0, SPECIAL1, SPECIAL2, SPECIAL3, SPECIAL4, SPECIAL5, ALL, NONE;

      /**
       * @return
       */
      public List<Faction> getCommonEnemies()
      {
         LinkedList<Faction> enemies = new LinkedList<Faction>();
         switch (this)
         {
            
            case COMMONER:
               enemies.add(ERUDITE);
            case ERUDITE:
               enemies.add(NOBLE);
            case NOBLE:
            case MILITARY:
               enemies.add(INFERNAL);
               enemies.add(REDBEAK);
               enemies.add(PIRATE);
               enemies.add(BANDIT);
               enemies.add(STRANDS_OF_THE_EAST);
               break;
               
               
            case HALFBLOOD:
               enemies.add(FULLBLOOD);
            case FULLBLOOD:
               enemies.add(HALFBLOOD);
               break;
               
            case CELESTIAL:
               enemies.clear();
               enemies.add(INFERNAL);
               break;
               
            case INFERNAL:
               Faction [] f = new Faction[] {COMMONER, MILITARY, TRAINER, CLERGY_GOOD, CLERGY_EVIL, NOBLE, HIGH_COMMONER, 
                        SLUM, REDBEAK, PIRATE, NORTHERNER, SOUTHERNER, WESTERNER, EASTERNER, MINIQUEST, CELESTIAL,
                        INFERNAL, BANDIT, ERUDITE, ABNEGATE, ARCANA, STRANDS_OF_THE_EAST, HALFBLOOD, FULLBLOOD};
               enemies.addAll(Arrays.asList(f));
               enemies.remove(INFERNAL);
               break;
         }
         
         return enemies;
      }
   }
}
