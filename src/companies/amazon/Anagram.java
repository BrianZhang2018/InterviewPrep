package companies.amazon;

/**
 * Created by brianzhang on 7/8/18.
 */
public class Anagram {

    public static boolean isAnagram(String s, String t) {

        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            c[t.charAt(i) - 'a']--;
        }
        System.out.println(c.length);
        for (int i = 0; i < c.length; i++) {
            if (c[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){


    }
}