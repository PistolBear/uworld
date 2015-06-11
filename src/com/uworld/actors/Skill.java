package com.uworld.actors;

import java.util.List;

import com.uworld.actors.IActor.AbilityName;
import com.uworld.main.NamedInteractable;

public abstract class Skill implements NamedInteractable
{
   Ability[] a_parentAbility;
   Double d_value = 0.0;

   /**
    * Individual instances of Skills must implement these methods.
    */
   public abstract void setName();

   public abstract IActor getOwner();

   public abstract void addAbility(AbilityName a);

   public abstract void setAbilities(List<Ability> a);

   public Ability[] getAbilities()
   {
      return a_parentAbility;
   }

   public String getLongDesc()
   {
      return s_descriptionLong;
   }

   public int doSkill()
   {
      int magnitude = 0;
      for (Ability eachAbility : a_parentAbility)
      {
         magnitude += (int) (eachAbility.getScore() * .75);
      }

      return magnitude;
   }

   public void setValue(Number value)
   {
      d_value = (double) value;      
   }
}