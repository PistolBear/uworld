/******************
 * UWorld File name: Author: PistolBear Created: May 12, 2015 Desc: Tags:
 */
package com.uworld.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.uworld.main.GameEngineInterface;

/**
 * A singleton class which logs data from the game. Data should be saved for
 * easy retrieval along with other game data.
 * 
 * @author PistolBear
 */
public class UWLogger
{
   public static final boolean DEBUG_MODE = GameEngineInterface.DEBUG_MODE;
   public static final boolean ALL_LOGGING = true;

   private static UWLogger m_baseInstance;
   private final StringBuffer m_buffer = new StringBuffer();
   private static int m_count = 0;
   private PrintStream m_local;

   private static final String UWLOGGER_DEBUG_HEADER = "#DEBUG LOGGING" + "\n#" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS zzz").format(new Date()) + "\n#ALL LOGGING: " + ALL_LOGGING + "\n#" + "\n#" + "\n\n";

   private static final String UWLOGGER_HEADER = "#LOGS" + "\n#" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS zzz").format(new Date()) + "\n#ALL LOGGING: " + ALL_LOGGING + "\n#" + "\n#" + "\n\n";

   private ArrayList<UWLogger> specialLogs;

   /**
    * @throws FileNotFoundException
    */
   public UWLogger() throws FileNotFoundException
   {
      m_local = new PrintStream(new File("log.txt"));
      
      if (DEBUG_MODE)
      {
         m_buffer.append(UWLOGGER_DEBUG_HEADER);
      }
      
      else
      {
         m_buffer.append(UWLOGGER_HEADER);
      }
      
   }

   private UWLogger(PrintStream p)
   {
      m_local = p;
   }

   public UWLogger(String special) throws FileNotFoundException
   {
      for (UWLogger logg : specialLogs)
      {
         if (logg == null)
         {
            logg = new UWLogger(new PrintStream(new File(special + ".txt")));
         }
      }
   }

   /**
    * @return
    */
   public static UWLogger getInstance()
   {
      if (m_baseInstance == null)
      {
         try
         {
            m_baseInstance = new UWLogger();
         }
         catch (FileNotFoundException e)
         {
            System.out.println("Cannot create a logger");
            return null;
         }
      }

      return m_baseInstance;
   }

   public void log(String s)
   {
      m_count++;
      m_buffer.append("\n" + s);

      m_local.println(m_buffer.toString());
      for(UWLogger special : specialLogs)
      {
         special.log(s);
      }
      
      m_buffer.delete(0, m_buffer.length() - 1);
   }

   public void destroy()
   {
      log("#End of logs#\n#############");
      m_local.flush();
      m_local.close();
   }

   public int getNumberOfUpdates()
   {
      return m_count;
   }

   /**
    * @param action
    * @param s
    * @param currentTimeMillis
    */
   public void log(LogType action, String s, long currentTimeMillis)
   {
      log("$ACTIONLOG: " + action.toString() + s + currentTimeMillis);
   }
   
   /**
    * @param action
    * @param s
    * @param currentTimeMillis
    */
   public void log(LogType action, String s, Object obj)
   {
      log("$ACTIONLOG: " + action.toString() + s + obj.toString());
   }
}
