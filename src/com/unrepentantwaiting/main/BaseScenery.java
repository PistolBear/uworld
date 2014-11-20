package com.unrepentantwaiting.main;

import java.util.List;

/**
 * This interface provides the groundwork for objects which may appear on-screen
 * (either GUI or in descriptive Console mode), but do not have any meaningful
 * interaction with the Actors. Things such as the ground, the distant scenery,
 * and unbreakable cavern walls will implement this. Most of the time, this
 * should be used in a single class to describe an entire scene which implements
 * this interface.
 * 
 * @author woody
 */
public interface BaseScenery
{
   public String getDescription();

   public boolean getImportant();

   public String getSpecial(BaseInteractable b); // For inspecting interactive
                                                 // items from the scene

   public List<BaseInteractable> getContainedInteractables();

   public List<BaseScenery> getContainedScenery();

}
