package affirm;

import java.util.*;

public class ShortestUncommonSubSequence {

    public static void main(String[] args) {
        ShortestUncommonSubSequence test = new ShortestUncommonSubSequence();
        System.out.println(test.getLengthOfShortestUncommonSubSeq("babab", "babba"));
        System.out.println(test.getSubSeqStringBruteForce("babab", "babba"));
    }

    public String getSubSeqStringBruteForce(String a, String b) {
        HashSet<String> subsets = new HashSet<>();
        backtrack(a, 0, subsets, new StringBuilder());

        List<String> list = new ArrayList<>(subsets);
        Collections.sort(list, Comparator.comparing(String::length));

        for (String str : list) {
            if (isNonSubSequence(str, b)) return str;
        }

        return "";
    }

    public void backtrack(String a, int start, Set<String> list, StringBuilder sb) {
        if (start > a.length()) return;

        list.add(new String(sb.toString()));

        for (int i = start; i < a.length(); i++) {
            sb.append(a.charAt(i));
            backtrack(a, i + 1, list, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean isNonSubSequence(String a, String b) {

        for (int i = 0; i < a.length(); i++) {
            int index = b.indexOf(a.charAt(i));
            if (index == -1) {
                return true;
            } else {
                b = b.substring(index + 1);
            }
        }
        return false;
    }

    // get length of shortest uncommon sub seq
    static final int MAX = 10000;

    public int getLengthOfShortestUncommonSubSeq(String a, String b) {
        int ans = shortestSeq(a.toCharArray(), b.toCharArray(), a.length(), b.length());
        if (ans >= MAX) ans = -1;
        return ans;
    }

    /* A recursive function to find the length of shortest uncommon subsequence*/
    static int shortestSeq(char[] S, char[] T, int m, int n) {
        // S String is empty
        if (m == 0) return MAX;

        // T String is empty
        if (n <= 0) return 1;

        // Loop to search for current character
        int k;
        for (k = 0; k < n; k++)
            if (T[k] == S[0])
                break;

        // character not found in T
        if (k == n) return 1;

        // Return minimum of following two situations
        // 1. Not including current char in answer
        // 2. Including current char
        return Math.min(
                shortestSeq(Arrays.copyOfRange(S, 1, S.length), T, m - 1, n), // Not including current char in answer
                1 + shortestSeq(Arrays.copyOfRange(S, 1, S.length), Arrays.copyOfRange(T, k + 1, T.length), m - 1, n - k - 1)
                //Including current char
        );
    }
}
