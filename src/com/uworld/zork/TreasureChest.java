/******************
 * UWorld ( 
 * 
 * File name: 
 * Author: PistolBear 
 * Created: Apr 24, 2015 
 * Desc:  A class experimenting with randomly generated loots.  Run main().
 * Tags:  REFACTOR
 ******************/
package com.uworld.zork;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author PistolBear
 */

public class TreasureChest implements IChest
{
   TreasureChest()
   {
      for (int i = 0; i < new Random().nextInt(50); i++)
      {
         m_contents.add(Treasure.randomThingy());
      }
   }

   List<Treasure> m_contents = new LinkedList<Treasure>();

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#peekContents()
    */
   @Override
   public List<Treasure> peekContents()
   {
      return m_contents;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#removeItem(com.uworld.zork.Treasure)
    */
   @Override
   public Treasure removeItem(Treasure t)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#placeItem(com.uworld.zork.Treasure)
    */
   @Override
   public boolean placeItem(Treasure t)
   {
      return false;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#dumpContents()
    */
   @Override
   public List dumpContents()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#lock()
    */
   @Override
   public boolean lock()
   {
      // TODO Auto-generated method stub
      return false;
   }

   /*
    * (non-Javadoc)
    * @see com.uworld.zork.IChest#unlock()
    */
   @Override
   public boolean unlock()
   {
      // TODO Auto-generated method stub
      return false;
   }

   /**
    * For test only.
    */
   public static void main(String[] args)
   {
      IChest chest = new TreasureChest();
      List<Treasure> l = chest.peekContents();
      
      for (Object t : l)
         System.out.println(t);
      
      if (l.isEmpty())
      {
         System.out.println("Nothing in this chest!");
      }
   }

}

/* default */abstract class Treasure
{
   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + Float.floatToIntBits(value);
      result = prime * result + Float.floatToIntBits(weight);
      return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Treasure other = (Treasure) obj;
      if (name == null)
      {
         if (other.name != null)
            return false;
      }
      else if (!name.equals(other.name))
         return false;
      if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
         return false;
      if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
         return false;
      return true;
   }

   StringBuffer name = new StringBuffer("");

   float value = 0f;

   float weight = 1f;

   /**
    * @param randomThingy
    */
   private void copy(Treasure randomThingy)
   {
      name = randomThingy.name;
      value = randomThingy.value;
      weight = randomThingy.weight;
      
   }

   public static Treasure randomThingy()
   {
      Treasure retTreasure = new Treasure(){ /*Insert Specialized stuff here */};
      
      int randoCalrissian = new Random().nextInt(200);
      do
      {
         retTreasure.randomizePrename();
         randoCalrissian = new Random().nextInt(randoCalrissian);
      }while (randoCalrissian % 3 != 0);
      retTreasure.name.append("Thingy");
      
      return retTreasure;
      
   }

   private void randomizePrename()
   {
      final int MAX_CASE = 23;
      switch (new Random().nextInt(MAX_CASE))
      {
         case 0:
            name.append("Strong ");
            value += 2f;
            break;
         case 1:
            name.append("Valiant ");
            value += 4f;
            break;
         case 2:
            name.append("Cursed ");
            value += 2f;
            break;
         case 3:
            name.append("Awkward ");
            value += .2f;
            weight += 1.4325f;
            break;
         case 4:
            name.append("Broken ");
            value += -1f;
            weight -= .22f;
            break;
         case 5:
            name.append("Glowing ");
            value += 6f;
            weight /= 2f;
            break;
         case 6:
            name.append("Exotic ");
            value += 3f;
            weight += .2f;
            break;
         case 7:
            name.append("Tiny ");
            value += 2f;
            weight /= 2f;
            break;
         case 8:
            name.append("Huge ");
            value += 2f;
            weight *= 2f;
            break;
         case 9:
            name.append("Righteous ");
            value += .1;
            weight *= 1.01;
            break;
         case 10:
            name.append("Unwieldy ");
            value -= .2;
            weight *= 1.05;
            break;
         case 11:
            name.append("Hurtful ");
            weight += 1;
            break;
         case 12:
            name.append("Merciful ");
            value += 2;
            weight *= .8;
            break;
         case 13:
            name.append("Exploding ");
            value += 10;
            weight *= 2;
            break;
         case 14:
            name.append("Grotesque ");
            value -= .4;
            weight *= 1.3;
            break;
         case 15:
            name.append("Mysterious ");
            value += 25;
            break;
         case 16:
            name.append("Furious ");
            weight *= 1.8;
            break;
         case 17:
            name.append("Mighty and ");
            value += 5;
            weight += 1;
         case 18:
            name.append("Thunderous ");
            value += 2;
            weight *= 1.4;
            break;
         case 19:
            name.append("Dripping ");
            value -= 1;
            weight *= 3;
            break;
         case 20:
            name.append("Spiked ");
            value += 5;
            weight *= 1.3;
            break;
         default:
            name.replace(0, name.length(), "Average "); value = 1f; weight = 1f; 
            break;
      }
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return name.toString() + ": " + value + "gp, " + weight + "lbs.";
   }
   
}