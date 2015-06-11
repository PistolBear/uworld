package com.uworld.main;

public interface IBaseInteractable
{
   public final boolean b_canInteract = true;

   public int getHealth();

   public void directDamage(int d);
   
   /**
    * This is the normal method for an interactable object to get
    * damaged.  This method's implementation should take into account
    * all forms of weaknesses, resistances, armor, etc. that the object
    * has vs the incoming DamageType.
    * @param d
    * @param damageType
    */
   void receiveDamage(int d, DamageType damageType);

   public void removeDamage(int d);

   public enum Size {
      INFINITESSIMAL, MINISCULE, TINY, SMALL, MEDIUM, LARGE, HUGE, GIGANTIC, COLLOSAL, COSMIC;
   }
}
