package com.uworld.main;

/**
 * The Named abstract class is for use with things that may not necessarily be
 * within the realm of being interacted with by the player, but may still want
 * to have a stored name and description. As such, BaseInteractable grabs these
 * methods too. Because of this, Named also is a parent to things like
 * Ability, Skill, etc.
 * 
 * @author woody
 */
public interface Named extends BaseInteractable
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
   
   public String getName();

   public String shortDesc();

   public String longDesc();

}