package com.unrepentantwaiting.actors;

import java.util.ArrayList;

public abstract class ActorFactoryInterface
{
   public static ArrayList<ActorInterface> defaultFactory(int i)
   {
      ArrayList<ActorInterface> actors = new ArrayList<ActorInterface>();
      for (int k = 0; k < i; k++)
      {
         // TODO: Create some actors
      }

      return actors;
   }

}
