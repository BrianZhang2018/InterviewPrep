package salesforce;

import java.util.*;

/**
 * solve this is to pad the integers to the same size by repeating the digits
 * then sort using these repeated integers as a sort key.
 *
 * Created by brianzhang on 11/4/21.
 */
public class HighestElementConcatenator {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"8", "89", "10", "15", "2"}));
    }

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Arrays.stream(nums).boxed().sorted( (a, b) -> {
            if(map.get(a) == map.get(b)) {
                return b-a;
            }

            return map.get(a) - map.get(b);
        });

        return nums;
    }

    public static String solution(String[] input) {
        Arrays.sort(input, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o2.compareTo(o1);
            }else{
                int l1 = o1.length(), l2 = o2.length();
                int max = Math.max(l1, l2);
                while(o1.length() < max) {
                    o1 += o1;
                }
                while(o2.length() < max) {
                    o2 += o2;
                }

                return o2.compareTo(o1);
            }
        });

        return String.join("", input);
    }

}
