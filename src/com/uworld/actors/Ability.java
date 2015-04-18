package com.uworld.actors;

import java.util.List;

import com.uworld.actors.IActor.AbilityName;
import com.uworld.main.Named;

public abstract class Ability implements Named
{
   private int i_score;

   private boolean b_nameInitialized = false;

   private String s_name;

   /**
    * Returns a List of the skills this ability affects. Will depend on
    * implementation.
    */
   public abstract List<String> getSkills();

   /**
    * getScore() Should have checks to see if i_score is initialized, and
    * perhaps that the asker has that kind of permission.
    * 
    * @return integer-precision score value.
    */
   public int getScore()
   {
      return i_score;
   }

   /**
    * setAbility Sets this ability's name. Does not change once it has been set.
    * 
    * @param an
    */
   public void setAbility(AbilityName an)
   {
      if (!b_nameInitialized)
      {
         b_nameInitialized = true;
         s_name = an.toString();
      }
      else
      {
         System.out.println("Invalid ability name request; Ability already initialized!");
         return;
      }
   }

   /**
    * Enforce AbilityNames to be used for the name.
    */
   @Override
   public final void setName(String s)
   {
      if (s.contains("AGI"))
         s = "AGI";
      if (s.contains("CON"))
         s = "CON";
      if (s.contains("KNO"))
         s = "KNO";
      if (s.contains("WIL"))
         s = "WIL";
      else
         s = "NON";

      switch (s)
      {
         case ("AGI"):
            setAbility(AbilityName.AGI);
            break;
         case ("CON"):
            setAbility(AbilityName.CON);
            break;
         case ("KNO"):
            setAbility(AbilityName.KNO);
            break;
         case ("WIL"):
            setAbility(AbilityName.WIL);
            break;
         default:
            setAbility(AbilityName.NON);
      }
   }
}
