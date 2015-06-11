/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: May 13, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.main;

/**
 * @author PistolBear
 *
 */
public abstract class Armor implements NamedInteractable
{
   int i_health, i_healthMax;
   StringBuffer s_name, d_desc, d_descLong;
   float f_weight;
   ArmorType at_type;
 
   /**
    * 
    */
   private Armor()
   {
      this("Nothing", ArmorType.NONE, 0f, 0);
   }
   
   protected Armor(String name, ArmorType at, float weight, int initialHealth)
   {
      s_name = new StringBuffer(name);
      at_type = at;
      f_weight = weight;
      i_health = i_healthMax = initialHealth;
   }
   
   /**
    * @return the f_weight
    */
   public float getF_weight()
   {
      return f_weight;
   }
  
   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#directDamage(int)
    */
   @Override
   public void directDamage(int d)
   {
      //TODO create system for determining damage absorbed by armor
   }
   
   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#getHealth()
    */
   @Override
   public int getHealth()
   {
      // TODO Auto-generated method stub
      return 0;
   }
   
   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#getName()
    */
   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   
   /* (non-Javadoc)
    * @see com.uworld.main.IBaseInteractable#receiveDamage(int, com.uworld.main.DamageType)
    */
   @Override
   public void receiveDamage(int d, DamageType damageType)
   {
      // TODO Auto-generated method stub
      
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
    * @see com.uworld.main.NamedInteractable#setDescription(java.lang.String)
    */
   @Override
   public void setDescription(String s)
   {
      // TODO Auto-generated method stub
      
   }
   
   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#setDescriptionLong(java.lang.String)
    */
   @Override
   public void setDescriptionLong(String s)
   {
      // TODO Auto-generated method stub
      
   }
   
   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#setName(java.lang.String)
    */
   @Override
   public void setName(String s)
   {
      // TODO Auto-generated method stub
      
   }
   
   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#getShortDesc()
    */
   @Override
   public String getShortDesc()
   {
      return d_desc.toString();
   }

   /* (non-Javadoc)
    * @see com.uworld.main.NamedInteractable#getLongDesc()
    */
   @Override
   public String getLongDesc()
   {
      return d_descLong.toString();
   }
   
   
   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((at_type == null) ? 0 : at_type.hashCode());
      result = prime * result + ((d_desc == null) ? 0 : d_desc.hashCode());
      result = prime * result + Float.floatToIntBits(f_weight);
      result = prime * result + i_healthMax;
      result = prime * result + ((s_name == null) ? 0 : s_name.hashCode());
      return result;
   }

   /* (non-Javadoc)
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
      Armor other = (Armor) obj;
      if (at_type != other.at_type)
         return false;
      if (d_desc == null)
      {
         if (other.d_desc != null)
            return false;
      }
      else if (!d_desc.equals(other.d_desc))
         return false;
      if (Float.floatToIntBits(f_weight) != Float.floatToIntBits(other.f_weight))
         return false;
      if (i_healthMax != other.i_healthMax)
         return false;
      if (s_name.toString() == null)
      {
         if (other.s_name.toString() != null)
            return false;
      }
      else if (!s_name.toString().equals(other.s_name.toString()))
         return false;
      return true;
   }

   /**
    * @return
    */
   public ArmorType getType()
   {
      return at_type;
   }
}
