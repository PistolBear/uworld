/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Jan 29, 2015
 * 
 * Desc:
 * Tags:
 */
package com.uworld.zork;

import java.util.LinkedList;

/**
 * @author PistolBear
 *
 */
public interface TextOutputMessageIF
{
   boolean print(Object ... args);
   

   abstract class MSG
   {
      private static final int MAX_HISTORY_SIZE = 40;
      private LinkedList<String> m_messageHistoryShort = new LinkedList<String>();
      private LinkedList<String> m_messageHistoryLong = new LinkedList<String>();

      public void test()
      {
         System.out.println("TextOutputMessageIF Test String");
         putHistory("TextOutputMessageIF Test String");
      }
      
      public final String[] getHistory()
      {
         return (String[]) m_messageHistoryShort.toArray();
      }
      
      protected final String[] getFullHistory()
      {
         return (String[]) m_messageHistoryLong.toArray();
      }
      
      private final boolean putHistory(String s)
      {
         boolean retVal = false;
         if (!m_messageHistoryShort.contains(s))
         {
            m_messageHistoryShort.add(s);
         }
         
         if (m_messageHistoryShort.size() > MAX_HISTORY_SIZE)
         {
            m_messageHistoryShort.pop();
            retVal = true;
         }
         
         m_messageHistoryLong.add(s);
         return retVal;
      }
   }
}
