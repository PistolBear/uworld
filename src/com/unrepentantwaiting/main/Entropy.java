/******************
 * UWorld 
 * 
 * File name:
 * Author: PistolBear
 * Created: Nov 20, 2014
 * 
 * Desc:
 * Tags:
 */
package com.unrepentantwaiting.main;

import java.util.Random;
import java.lang.reflect.Field;

/**
 * @author PistolBear
 *
 */
public abstract class Entropy
{
   public final int getEntropyPlus(int i)
   {
      Byte b = (byte) i;
      i *= b + (Integer) (new Random(System.nanoTime() + System.currentTimeMillis() / System.identityHashCode(b)).nextInt(127));
      
      return Math.abs(i);
   }
   
   public final int getEntropyMinus(int i)
   {
      return getEntropyPlus(i) * -1;
   }
   
   public final int getEntropy(int i)
   {
      float f = new Random().nextFloat() * Float.parseFloat(Random.class.getName().substring(new Random().nextInt(Random.class.getName().length())));
      return (System.nanoTime() % 2 == 0) ? getEntropyPlus((int) (i-f)) : getEntropyMinus((int) (i+f/2));
   }
   
   public final int getEntropy()
   {
      return getEntropy((int) System.nanoTime() / Integer.parseInt(System.getenv().get(System.getenv().keySet().size() - 1)));
   }
}
