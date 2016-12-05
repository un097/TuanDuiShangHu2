package techown.shanghu.biaoge;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmConvert {
	private static String[] AlgorithmTable = new String[] { "0", "1", "2", "3",
		"4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
		"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
		"u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
		"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z" };
	
	 public static String backtonum(String a)
     {
         List<Long> lo = new ArrayList<Long>(0);
         char[] ca=a.toCharArray();
         for (int j = 0; j < 7; j++)
         {
             for (int i = 0; i < AlgorithmTable.length; i++)
             {
                 if (AlgorithmTable[i].equals(String.valueOf(ca[j])))
                 {
                     lo.add(j,(long)i);
                 }
             }
         }


         long temp = lo.get(0) * 62 * 62 * 62 * 62 * 62 * 62;
         temp += lo.get(1) * 62 * 62 * 62 * 62 * 62;
         temp += lo.get(2) * 62 * 62 * 62 * 62;
         temp += lo.get(3)* 62 * 62 * 62;
         temp += lo.get(4) * 62 * 62;
         temp += lo.get(5) * 62;
         temp += lo.get(6);
         String str =String.valueOf(temp);
         int len = 13 - str.length();
         for (int i = 0; i < len; i++)
         {
             str = "0" + str;
         }
         return str;
     }
}
