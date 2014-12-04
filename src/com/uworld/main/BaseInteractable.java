package com.uworld.main;

public interface BaseInteractable
{
   public final boolean b_canInteract = true;

   public int getHealth();

   public void directDamage(int d);
   
   void indirectDamage(int d, DamageType damageType);

   public void removeDamage(int d);

   public enum Size {
      INFINITESSIMAL, MINISCULE, TINY, SMALL, MEDIUM, LARGE, HUGE, GIGANTIC, COLLOSAL, COSMIC;
   }
}
