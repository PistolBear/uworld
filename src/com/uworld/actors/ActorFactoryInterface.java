package com.uworld.actors;

import java.util.ArrayList;

public abstract class ActorFactoryInterface
{
   public static ArrayList<IActor> defaultFactory(int i)
   {
      ArrayList<IActor> actors = new ArrayList<IActor>();
      for (int k = 0; k < i; k++)
      {
         // TODO: Create some actors
      }

      return actors;
   }

}
