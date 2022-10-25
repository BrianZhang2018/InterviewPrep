package companies.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 1/30/19.
 */
public class FindIndexOfRange {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

        /*find("AABACABCAEGADABC", 0, "ABC");
        for (int[] v : res) {
            for (Integer i : v) {
                System.out.println(i);
            }
        }*/

        String str = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] ha = str.split(" |\\,|\\.");

        String[] arr = {"nice", "haha", "cool"};
        //System.out.println(arr.c);
    }

    public static int trap(int[] height) {
        int sum = 0;
        if (height == null || height.length == 0)
            return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        maxLeft[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    static List<int[]> res = new ArrayList<int[]>();

    public static void find(String str, int start, String word) {

        if (str.length() - start < word.length()) {
            return;
        }
        if (word.equals(str.substring(start, start + word.length()))) {
            res.add(new int[]{start, start + word.length() - 1});
        }
        find(str, start + 1, word);
    }
}
