package com.unrepentantwaiting.actors;

public interface ActorNPC extends ActorInterface
{
   public boolean getLootable();

   public boolean hasDialogue();

   public void addDialogue(DialogueInterface di);

}
