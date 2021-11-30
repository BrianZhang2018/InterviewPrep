package square.onsite;

import java.util.*;

/**
 * https://leetcode.com/problems/palindrome-pairs/discuss/79199/150-ms-45-lines-JAVA-solution
 *
 * Step 1: store every word with its index into a hash map.
 * Step 2: For each word in the array, split into two parts str1 and str2. Check whether str1 and str2 is palindrome.
 * If str1 is palindrome, we can use str1 as middle part, str2 as right part, and find if map contains reversed str2.
 * If contains, then we can use that string as left part, combine with middle part, right part, it will form a correct palindrome string.
 * Step 3: do all same operations for str2 (set str2 as middle part)
 * */
public class PalindromePair {
    public static void main(String[] args) {
        String[] input = {"abcd","dcba","lls","s","sssll"};
        for(int[] i : new PalindromePair().palindromePairs(input)){
            System.out.println(i[0] + " : " + i[1]);
        }
    }

    // I changed the return type to make the code more clean and easy
    public List<int[]> palindromePairs(String[] words) {
        List<int[]> result = new ArrayList<>();
        if (words.length == 0) return result;

        // step1: store every word with its index into a hash map.
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) { // words loop
            for (int j = 0; j <= words[i].length(); j++) { // split word loop, careful j "<=" words[i].length()
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);

                // step 2
                if (isPalindrome(str1)) {
                    String reversedStr2 = new StringBuilder(str2).reverse().toString();

                  /* WARNING: MUST CHECK whether str.length() is equal to 0 in either if statement, because we need to make sure
                   * we do not add duplicate pairs (if str1.length() == 0, both of str1 and str2 will from input array) */
                  // map.get(reversedStr2) != i make sure don't take itself to make palindrome
                    if (map.containsKey(reversedStr2) && map.get(reversedStr2) != i && str1.length() != 0) {
                        result.add(new int[]{map.get(reversedStr2), i}); // word + "ll" + "s"
                    }
                }

                // step 3
                if (isPalindrome(str2)) {
                    String reversedStr1 = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(reversedStr1) && map.get(reversedStr1) != i) {
                        result.add(new int[]{i, map.get(reversedStr1)}); // "ll" + "s" + word
                    }
                }
            }
        }
        return result;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
