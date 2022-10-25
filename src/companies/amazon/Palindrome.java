package companies.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brianzhang on 7/7/18.
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aab"));
    }

    public static boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else
                set.remove(s.charAt(i));
        }
        return set.size() == 0 || set.size() == 1;
    }
}
