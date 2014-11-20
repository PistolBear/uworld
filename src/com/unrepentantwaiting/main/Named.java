package com.unrepentantwaiting.main;

/**
 * The Named abstract class is for use with things that may not necessarily be
 * within the realm of being interacted with by the player, but may still want
 * to have a stored name and description. As such, BaseInteractable grabs these
 * methods too. Because of this, NamedObject also is a parent to things like
 * Ability, Skill, etc.
 * 
 * @author woody
 */
public abstract class Named implements BaseInteractable
{
   protected String s_name;

   protected String s_descriptionShort = "Object description";

   protected String s_descriptionLong = "";

   /**
    * All NamedObjects should be able to send their name and a short
    * description.
    */
   public abstract void setName(String s);

   public abstract void setDescription(String s);

   /**
    * Do not enforce a long description for things that don't need one. Keeping
    * this here is more for quick reference.
    */
   public void setDescriptionLong()
   {
   }

   public String getName()
   {
      if (s_name == null)
         s_name = "Object Name";

      if (s_name.isEmpty())
         s_name = "Object Name";

      return s_name;
   }

   public String shortDesc()
   {
      return s_descriptionShort;
   }

   public String longDesc()
   {
      return s_descriptionLong;
   }

}