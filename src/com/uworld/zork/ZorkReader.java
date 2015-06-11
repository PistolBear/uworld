/******************
 * 
 * UWorld 
 * 
 * File name: ZorkReader.java
 * Author: PistolBear Created: Mar 24, 2015 Desc: Tags:
 *
 ******************/
package com.uworld.zork;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.uworld.main.GameEngine;

/**
 * @author PistolBear
 */
public class ZorkReader implements TextInputMessageIF
{
   private boolean ECHO = false;

   /*Default*/ ZorkReader()
   {
      // Default echo behavior
      this(true);
   }

   /*Default*/ ZorkReader(boolean echoStatements)
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
   private String tokenize(String input)
   {

      if (input == null || input.length() == 0)
      {
         return "NOTHING";
      }

      ArrayList<String> tokens = new ArrayList<>();
      parseStrings(input, tokens);

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
      
      response.append(ZorkGameEngine.getZorkInstance().handleTokens(tokens));
      return response.toString();
   }

   /**
    * Parse through strings and turn them into tokens
    * that we can deal with individually. 
    * @param input
    * @param tokens
    */
   private void parseStrings(String input, ArrayList<String> tokens)
   {
      input = input.trim().toLowerCase() + ' ';
      
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
   }

   /**
    * debugInfo
    * 
    * We have to jump through some hoops to avoid trying to print non-printing
    * characters such as Shift. (Not only do they not print, but if you put them
    * in a String, the characters afterward won't show up in the text area.)
    * @param zorkWindow TODO
    * @param e TODO
    * @param keyStatus TODO
    */
   /*Default*/ void debugInfo(ZorkWindow zorkWindow, KeyEvent e, String keyStatus)
   {
      // You should only rely on the key char if the event
      // is a key typed event.
      int id = e.getID();
      String keyString;
      if (id == KeyEvent.KEY_TYPED)
      {
         char c = e.getKeyChar();
         keyString = "key character = '" + c + "'";
      }
      else
      {
         int keyCode = e.getKeyCode();
         keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
      }
   
      int modifiersEx = e.getModifiersEx();
      String modString = "extended modifiers = " + modifiersEx;
      String tmpString = KeyEvent.getModifiersExText(modifiersEx);
      if (tmpString.length() > 0)
      {
         modString += " (" + tmpString + ")";
      }
      else
      {
         modString += " (no extended modifiers)";
      }
   
      String actionString = "action key? ";
      if (e.isActionKey())
      {
         actionString += "YES";
      }
      else
      {
         actionString += "NO";
      }
   
      String locationString = "key location: ";
   
      // Standard means number keys on the top row, otherwise they'll be on the
      // numpad (usually).
      int location = e.getKeyLocation();
      if (location == KeyEvent.KEY_LOCATION_STANDARD)
      {
         locationString += "standard";
      }
      else if (location == KeyEvent.KEY_LOCATION_LEFT)
      {
         locationString += "left";
      }
      else if (location == KeyEvent.KEY_LOCATION_RIGHT)
      {
         locationString += "right";
      }
      else if (location == KeyEvent.KEY_LOCATION_NUMPAD)
      {
         locationString += "numpad";
      }
      else
      { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
         locationString += "unknown";
      }
   
      zorkWindow.outputArea.append(keyStatus + ZorkWindow.NEWLINE 
               + "    " + keyString + ZorkWindow.NEWLINE + "    " 
               + modString + ZorkWindow.NEWLINE + "    " + actionString 
               + ZorkWindow.NEWLINE + "    " + locationString + ZorkWindow.NEWLINE);
      
      zorkWindow.outputArea.setCaretPosition(zorkWindow.outputArea.getDocument().getLength());
   }

   /*Default*/ void handleKeyPress(ZorkWindow zorkWindow, KeyEvent e)
   {
      String outputStringFromEngine;
      if (KeyEvent.VK_ENTER == e.getKeyCode())
      {
         // tokenize(...) converts finished phrases into 
         // engine-readable codes which can be used to 
         // pass on special instructions.
         outputStringFromEngine = tokenize(zorkWindow.m_inputField.getText());
   
         createEngineCodeFromToken(zorkWindow, outputStringFromEngine);
         
         // When we're done tokenizing, handling the tokens,
         // and outputting any results, we want to clear the inputField
         // and set focus to it (in case it was lost to something else).
         zorkWindow.m_inputField.setText("");
         zorkWindow.m_inputField.requestFocusInWindow();
   
      }
   
      else if (e.isActionKey())
      {
         outputStringFromEngine = tokenize(KeyEvent.getKeyText(e.getKeyCode()));
         zorkWindow.outputArea.append(ZorkWindow.NEWLINE + outputStringFromEngine + ZorkWindow.NEWLINE);
         zorkWindow.outputArea.setCaretPosition(zorkWindow.outputArea.getDocument().getLength());
   
      }
   }

   private void createEngineCodeFromToken(ZorkWindow zorkWindow, String input)
   {
      // Handle exit commands first specially.
      switch(input)
      {
         case GameEngine.EXIT_COMMAND:
            // Insert save request code here.
            zorkWindow.outputArea.setText("Exiting...");
            zorkWindow.dispose();
            break;
         case GameEngine.DICE_INPUT:
            zorkWindow.outputArea.setText("Rolling...");
            zorkWindow.outputArea.setCaretPosition(zorkWindow.outputArea.getDocument().getLength());
         default:
            zorkWindow.outputArea.append(ZorkWindow.NEWLINE + "Not sure what to do with: " + input + ZorkWindow.NEWLINE);
            zorkWindow.outputArea.setCaretPosition(zorkWindow.outputArea.getDocument().getLength());
            break;
      }
   }

}
