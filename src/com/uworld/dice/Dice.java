/******************
 * UWorld 
 * 
 * File s_name:
 * Author: PistolBear
 * Created: May 13, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.dice;


/**
 * @author PistolBear
 *
 */
public class Dice
{
   private String s_name = "D20";
   private int i_max = 20;
   int privateWeight = 0;
   int publicWeight = 0;
   int i_mod = 0;
   
   
   public static void main(String [] s)
   {
      System.out.println(d100().getName() + ": " + d100());
      System.out.println(d20().getName() + ": " + d20());
      System.out.println(d12().getName() + ": " + d12());
      System.out.println(d10().getName() + ": " + d10());
      System.out.println(d8().getName() + ": " + d8());
      System.out.println(d6().getName() + ": " + d6());
      System.out.println(d4().getName() + ": " + d4());
      System.out.println(d3().getName() + ": " + d3());
      System.out.println(d2().setMod(2).getName() + ": " + d2().setMod(2));
      System.out.println(d6().setMod(7).getName() + ": " + d6().setMod(7));
   }
   
   
   
   /**
    * @return
    */
   private String getName()
   {
      return s_name;
   }

   /**
    * @param i
    * @return
    */
   public Dice setMod(int i)
   {
      i_mod = i;
      s_name += "+" + i;
      return this;
   }

   /**
    * 
    */
   @Override
   public String toString()
   {
      return new String(Integer.toString(this.roll()));
   }
   
   /**
    * @return the value of the die.  Standard for the toString() method.
    */
   public int roll()
   {
      long x = System.nanoTime();
      x ^= (x << 21);
      x ^= (x >>> 35);
      x ^= (x << 4);
      return Math.abs((int)x % i_max) + 1 + i_mod;
   }

   /**
    * @return
    */
   public static Dice d2()
   {
      return new Dice().setMax(2);
   }

   /**
    * @param i
    * @return
    */
   private Dice setMax(int i)
   {
      s_name = "D"+i;
      if (i_mod != 0)
      {
         this.setMod(i_mod);
      }
      i_max = i;
      return this;
   }

   /**
    * @return
    */
   public static Dice d3()
   {
      return new Dice().setMax(3);
   }

   /**
    * @return
    */
   public static Dice d4()
   {
      return new Dice().setMax(4);
   }

   /**
    * @return
    */
   public static Dice d6()
   {
      return new Dice().setMax(6);
   }

   /**
    * @return
    */
   public static Dice d8()
   {
      return new Dice().setMax(8);
   }

   /**
    * @return
    */
   public static Dice d10()
   {
      return new Dice().setMax(10);
   }

   /**
    * @return
    */
   public static Dice d12()
   {
      return new Dice().setMax(12);
   }

   /**
    * @return
    */
   public static Dice d20()
   {
      return new Dice();
   }

   /**
    * @return
    */
   public static Dice d100()
   {
      return new Dice().setMax(100);
   }

}
