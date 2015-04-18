/******************
 * UWorld File name: Author: PistolBear Created: Mar 24, 2015 Desc: Tags:
 */
package com.uworld.zork;

import java.awt.event.KeyEvent;
import java.io.Console;
import java.util.ArrayList;

/**
 * @author PistolBear
 */
public class ZorkParser implements TextInputMessageIF
{
   private boolean ECHO = false;

   ZorkParser()
   {
      // Default echo behavior
      this(true);
   }

   ZorkParser(boolean echoStatements)
   {
      ECHO = echoStatements;
   }

   @Override
   public String readln()
   {
      return null;
   }

   @Override
   public String prompt(String q)
   {
      return q;
   }

   /**
    * @param input
    */
   public String tokenize(String input)
   {

      if (input == null || input.length() == 0)
      {
         return "NOTHING";
      }

      ArrayList<String> tokens = new ArrayList<>();
      input = input.trim().toLowerCase() + " ";

      // Parse through strings and turn them into tokens that we can deal with
      // individually
      for (int i = 0; i < input.length();)
      {
         if (input.charAt(i) == ' ' || i == input.length())
         {
            tokens.add(input.substring(0, i));
            input = input.substring(i, input.length());
            i = 0;
         }

         i++;
      }

      StringBuilder response = new StringBuilder();

      if (ECHO)
      {
         response.append("Input received: ");

         for (String each : tokens)
         {
            response.append(each);
            response.append(' ');
         }

         response.append("\n\n");
      }

      response.append(ZorkGameEngine.getGameEngine().handleTokens(tokens));
      return response.toString();
   }

}
