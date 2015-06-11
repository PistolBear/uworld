package com.uworld.main;

/**
 * The NamedInteractable abstract class is for use with things that may not necessarily be
 * within the realm of being interacted with by the player, but may still want
 * to have a stored name and description. As such, IBaseInteractable grabs these
 * methods too. Because of this, NamedInteractable also is a parent to things like
 * Ability, Skill, etc.
 * 
 * @author woody
 */
public interface NamedInteractable extends IBaseInteractable
{
   String s_descriptionShort = "Object description";

   String s_descriptionLong = "";

   /**
    * All NamedObjects should be able to send their name and a short
    * description.
    */
   public void setName(String s);

   public void setDescription(String s);

   /**
    * Do not enforce a long description for things that don't need one. Keeping
    * this here is more for quick reference.
    */
   public void setDescriptionLong(String s);
   
   /**
    * @return  A name, like "Greatsword" or "Jimmy".
    */
   public String getName();

   /**
    * @return A short description of this thing 
    */
   public String getShortDesc();

   /**
    * @return A longer description of this thing.  May have important info.
    */
   public String getLongDesc();

}