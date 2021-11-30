package microsoft.ms2019;

import java.util.HashSet;
import java.util.Set;

/**
 * "Fuck up point is the "getStringWithNoThreeIdenticalConsecutiveLetter", My string manipulation is suck"
 * <p>
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=544667&page=1#pid8444331
 * <p>
 * Created by brianzhang on 10/17/19.
 */
public class OAStringProblems {

    public static void main(String[] args) {
        // problem - 1
      /*  System.out.println('a' - 'A');
        System.out.println(getLargestCharacter("aAbBz"));
*/
        // problem - 2
        System.out.println(findConsecutiveSubstringWithLessThanTwoConsecutiveCharacters("baaabbabbb"));

        // problem -3
        //System.out.println(getLeastString("acbz"));

        //System.out.println("acb".compareTo("abz"));

        //problem -4 largest Integer
        // System.out.println(getLargestInteger(new int[]{3, 2, -2, 5, -3}));

        System.out.println(getStringWithNoThreeIdenticalConsecutiveLetter("acbbbccddd"));
    }

    //delete the letter to make a string that no three identical consecutive letters
    public static String getStringWithNoThreeIdenticalConsecutiveLetter(String S) {

        if (S.length() <= 2) {
            return S;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(S.charAt(0));

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i - 1) == S.charAt(i)) {
                count++;
            } else {
                while (count > 2) {
                    sb.deleteCharAt(sb.length() - 1);
                    count--;
                }
                count = 1;
            }

            sb.append(S.charAt(i));   //重点: incrementally append the letter into a string
        }

        while (count > 2) {
            sb.deleteCharAt(sb.length() - 1);
            count--;
        }

        return sb.toString();
    }


    public static String getLeastString(String s) {

        if (s == null || s.length() <= 1)
            return s;

        char curr;
        char pre = s.charAt(0);
        int index = 1;

        StringBuilder sb = new StringBuilder(s);
        while (index < s.length()) {
            curr = s.charAt(index);
            if (curr > pre) {
                index++;
                pre = curr;
            } else {
                sb.deleteCharAt(index - 1);
                break;
            }

            if (index == s.length())
                sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    //"baaabbabbb" -> "aabbabb" (7)
    public static int findConsecutiveSubstringWithLessThanTwoConsecutiveCharacters(String s) {

        if (s.length() < 3)
            return s.length();

        // Initialize temporary and final ans to 2 as this is the minimum length of substring when length of the given string is greater than 2
        int temp = 2;
        int ans = 2;

        // Traverse the string from the third character to the last
        for (int i = 2; i < s.length(); i++) {
            // If no three consecutive characters are same then increment temporary count
            if (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2))
                temp++;
            else  // Else updateHotNodes the final ans and reset the temporary count
            {
                ans = Math.max(temp, ans);
                temp = 2;
            }
        }

        ans = Math.max(temp, ans);
        return ans;
    }

    public static char getLargestCharacter(String s) {
        char[] input = s.toCharArray();
        char max = ' ';
        boolean[] lowerC = new boolean[26];
        for (char c : input) {
            if (Character.isLowerCase(c)) {
                lowerC[c - 'a'] = true;
            }

            if (Character.isUpperCase(c)) {
                if (lowerC[c + 32 - 'a'] == true && c > max) {
                    max = c;
                }
            }
        }

        return max;
    }

    public static int getLargestInteger(int[] arr) {

        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(-arr[i]);
            if (set.contains(arr[i])) {
                max = Math.max(max, Math.abs(arr[i]));
            }
        }
        return max;
    }

    /*
    public static int findConsecutiveSubstringWithLessThanTwoConsecutiveCharacters(String s){

        if(s.length() < 3){
            return s.length();
        }
        int left=0, right=0, pre = 0;
        int count = 0;
        int max = 0;

        while(right < s.length()){
            char cr = s.charAt(right);
            char cp = s.charAt(pre);

            if(cr == cp && count + 1 > 2){
                if(right - left > max)
                    max = right - left;

                left = right - 1;
                pre = right;
                count = 2;
            }else if(cr == cp){
                count++;
            }else{
                count = 1;
                pre =right;
            }
            right++;
        }

        return max;
    }*/

}
