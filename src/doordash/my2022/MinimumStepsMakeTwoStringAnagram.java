package doordash.my2022;

public class MinimumStepsMakeTwoStringAnagram {
    public static void main(String[] args) {
        System.out.println(minSteps("leetcode", "practice"));
    }

    public static int minSteps(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        int sum = 0;
        for (int i : count) {
            if (i > 0) sum += i;
        }

        return sum;
    }
}
