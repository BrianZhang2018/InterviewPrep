package upstart;

import java.util.*;

public class LongestSubArray {

    public static void main(String[] args) {
        System.out.println(maxLength(Arrays.asList(1, 2, 3), 3));
    }

    public static int maxLength(List<Integer> list, int k) {
        int l = 0, r = 0;
        int sum = 0;
        int maxLength = Integer.MIN_VALUE;
        while (r < list.size() && l <= list.size()) {
            sum += list.get(r);
            if (sum <= k) {
                maxLength = Math.max(maxLength, r - l + 1);
                r++;
            } else {
                sum -= list.get(l++);
            }
        }

        if (sum < k) {
            maxLength = Math.max(maxLength, r - l + 1);
        }

        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }

}
