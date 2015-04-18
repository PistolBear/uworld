package com.uworld.actors;

public interface ActorNPC extends IActor
{
   public boolean getLootable();

   public boolean hasDialogue();

   public void addDialogue(DialogueInterface di);

}
