package hubspot.codingOnsite;

import java.util.*;
import java.util.stream.Collectors;

public class Ngram {
    public static void main(String[] args) {
        System.out.println(helper("ababablkie", 3));
    }

    // just return one or all
    public static List<String> helper1(String s, int n) {
        if(s == null || s.length() == 0 || n > s.length()) return Collections.emptyList();

        Map<String, Integer> count = new HashMap<>();
        for(int i=0; i<=s.length()-n; i++) {
            String curr = s.substring(i, i+n+1);
            count.put(curr, count.getOrDefault(curr, 0) + 1);
        }

        count.entrySet().stream().
                filter(e -> e.getValue() == Collections.max(count.entrySet(), Comparator.comparingInt(entry -> entry.getValue())).getValue())
                .collect(Collectors.toList());

        int max = 0;
        for(Map.Entry<String, Integer> entry : count.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        int finalMax = max;
        return count.entrySet().stream()
                .filter(e -> e.getValue() == finalMax)
                .map(e->e.getKey()).
                collect(Collectors.toList());

    }

    public static String helper(String s, int n) {
        if (s == null || s.length() == 0 || n > s.length()) return "";
        if (n >= s.length()) return s;

        HashMap<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i <= s.length() - n; i++) {
            String cur = s.substring(i, i + n);
            countMap.put(cur, countMap.getOrDefault(cur, 0) + 1);
        }

        // return Collections.max(countMap.entrySet(), Comparator.comparingInt(entry -> entry.getValue())).getKey(); // O(n)

        int max = 0;
        String result = "";
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }
}