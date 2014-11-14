package com.sideprojects.unrepentantwaiting.actors;

/**
 * Dialogue Interface provides the framework for creating Dialogue objects that
 * can be then attached to any NPC (or other object that can hold a
 * DialogueInterface) and will smartly change their type, language, jargon, etc.
 * to appropriate values depending on what the owner is. For instance, one could
 * make a ShopDialogue object once, and place it both on a dragon sailor and on
 * a duergar, both of which would then have similar responses, but may say them
 * in different languages, temperance, and jargon.
 * 
 * @author wphillips
 */
public interface DialogueInterface
{
   public String m_baseGreeting = "Hi";

   public DialogueJargon getJargon();

   public DialogueLanguage getLanguage();

   public DialogueEmotion getEmotion();

   public DialogueType getDialogueType();

   public enum DialogueType {
      NONE, GREETING, FAREWELL, SHOP, BATTLECRY;
   }

   public enum DialogueEmotion {
      NONE, ANGRY, SAD, HAPPY, APATHETIC;
   }

   public enum DialogueLanguage {
      NONE, ENGLISH, JAPANESE, RUSSIAN, TENGWAR, COMMON, ORCISH, GOBLIN, SIGN, GUTTERSPEAK, PHEROM, CELESTIAL, INFERNAL, ABYSSMAL, GNOME, DWARVEN, DRACONIC, VOID, KRAFTWERK, HALFLING, AQUAN, TERRAN, IGNAN, AURAN, MINDSPEAK, TITAN, DRUIDIC, FEY, CHRONOLOGOS, JABBER, UNDERCOMMON, SALTSPEAK, ALLTONGUE;
   }

   public enum DialogueJargon {
      NONE, TRADE, SAILOR, ARCANIST, POLITICIAN, NOBILITY, COMMONFOLK, SLUMFOLK;
   }
}
