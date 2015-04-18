/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Apr 9, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.actors;

/**
 * @author PistolBear
 *
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum Faction
{
   // Various standard NPCs
   COMMONER(0), MILITARY(2), TRAINER(-1), CLERGY_GOOD(-2), MINIQUEST(0), 
   CLERGY_EVIL(1), SLUM(1), 
   
   // Brigands of various types
   REDBEAK(3), PIRATE(2),  BANDIT(2),   
   
   // Strangers from a strange land
   STRANDS_OF_THE_EAST(2),  
   
   // Folks from not too far away
   NORTHERNER(1), SOUTHERNER(1), WESTERNER(0), EASTERNER(0), 
    
   // Spiritual beings
   CELESTIAL(0), INFERNAL(3), 
   
   // More important NPCs
   ERUDITE(-2), ABNEGATE(-1), ARCANA(0), NOBLE(-1), HIGH_COMMONER(-1), ROYALTY(0), 
   
   // Werecreatures
   HALFBLOOD(1), FULLBLOOD(-1),
   
   // Special
   SPECIAL0(0), SPECIAL1(1), SPECIAL2(2), SPECIAL3(3), SPECIAL4(4), SPECIAL5(5), ALL(0), NONE(-5);

   // Initial aggro towards player based on this value.  These values are cumulative among all factions
   // that an actor may possess.
   int m_aggressiveness = 0;
   
   Faction(int aggressiveness)
   {
      m_aggressiveness = aggressiveness;
   }
   
   int getAggroRating()
   {
      return m_aggressiveness;
   }
   
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
            enemies.add(INFERNAL);
            enemies.add(REDBEAK);
            enemies.add(PIRATE);
            enemies.add(BANDIT);
            enemies.add(STRANDS_OF_THE_EAST);
            break;
            
         case NOBLE:
            enemies.add(COMMONER);
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
            break;

         case PIRATE:
         case BANDIT:
            enemies.add(TRAINER);
            enemies.add(WESTERNER);
         case SLUM:
            enemies.add(ERUDITE);
            enemies.add(NOBLE);
         case STRANDS_OF_THE_EAST:
            enemies.add(ARCANA);
            enemies.add(MILITARY);
            enemies.add(EASTERNER);
            enemies.add(INFERNAL);
            enemies.add(CELESTIAL);
         case ABNEGATE:
            break;
            
         case ALL:
            enemies.add(ALL);
            break;
         
         case ARCANA:
            enemies.add(INFERNAL);
            enemies.add(CELESTIAL);
            enemies.add(MILITARY);
            break;
         
         case CLERGY_EVIL:
            enemies.add(CLERGY_GOOD);
            enemies.add(CELESTIAL);
            break;
         
         case CLERGY_GOOD:
            enemies.add(CLERGY_EVIL);
            enemies.add(INFERNAL);
            break;
         
         case EASTERNER:
            enemies.add(WESTERNER);
            enemies.add(SOUTHERNER);
            break;
         
         case HIGH_COMMONER:
            enemies.add(COMMONER);
            break;
         
         case MINIQUEST:
            break;
         
         case NORTHERNER:
            enemies.add(EASTERNER);
            enemies.add(SOUTHERNER);
            break;
         
         case REDBEAK:
            break;
         
         case ROYALTY:
            enemies.add(NONE);
            break;
         
         case SOUTHERNER:
            enemies.add(WESTERNER);
            enemies.add(NORTHERNER);
            break;
         
         case SPECIAL0:
            break;
         
         case SPECIAL1:
            break;
         
         case SPECIAL2:
            break;
         
         case SPECIAL3:
            break;
         
         case SPECIAL4:
            break;
         
         case SPECIAL5:
            break;
         
         case TRAINER:
            break;
         
         case WESTERNER:
            enemies.add(EASTERNER);
            enemies.add(SOUTHERNER);
            break;
         
         default:
         case NONE:
            enemies.clear();
      }
      
      return enemies;
   }
}