package expedia;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 *
 * Created by brianzhang on 9/6/21.
 */
public class MaximumAreaOfPieceOfCake {

    public static void main(String[] args) {
        System.out.println(maxArea(5, 4, new int[]{1,2,4}, new int[]{1,3}));
    }

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long hl = getMaxDiff(horizontalCuts, h); // use "long" here
        long vl = getMaxDiff(verticalCuts, w);
        return (int)((hl * vl) % 1000000007);
    }

    private static long getMaxDiff(int[] arr, int end) {
        Arrays.sort(arr);
        int len = 0, start = 0;
        for (int i : arr) {
            len = Math.max(i - start, len);
            start = i;
        }
        len = Math.max(len, end - start);
        return len;
    }

}
