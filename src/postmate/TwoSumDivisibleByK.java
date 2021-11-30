package postmate;

import java.util.*;

/**
 * Map<余数, number>
 *
 * Created by brianzhang on 7/12/20.
 */
public class TwoSumDivisibleByK {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 3, 11, 8}, 5)));
        System.out.println(Arrays.toString(solution(new int[]{-4, 3, -11, 8}, 5)));
    }

    public static int[] solution(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int mod = num % k;
            if (mod > 0) {
                if (map.containsKey(k - mod)) {
                    return new int[]{num, map.get(k - mod)};
                }
            }

            if (mod < 0) {
                if (map.containsKey(-k - mod)) {
                    return new int[]{num, map.get(-k - mod)};
                }
            }
            map.put(num % k, num);
        }

        return null;
    }
}
