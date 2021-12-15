package roblox;

import java.util.*;

// [1,3,5,8,3,7,2] -> [8,7]
// [3,1,5] -> [3,5]
// [1,4,5,5,5,5] -> [5,5,5,5]
public class FindAllPeakElement {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findAllPeaks(new int[]{1,3,5,8,3,7,2})));
        System.out.println(Arrays.toString(findAllPeaks(new int[]{3,1,5})));
        System.out.println(Arrays.toString(findAllPeaks(new int[]{1,4,5,5,5,5,5})));
    }

    public static Integer[] findAllPeaks(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        if(nums.length == 1) return new Integer[nums[0]];
        List<Integer> res = new ArrayList();

        int left = Integer.MIN_VALUE, curr = 0, right = 1;
        while(right < nums.length) {
            if(left < nums[curr] && nums[curr] > nums[right]) { // peak
                res.add(nums[curr]);
            }

            if(left < nums[curr] && nums[curr] == nums[right]) {
                while(right < nums.length && nums[curr] == nums[right]) {
                    right++;
                }
                if(right == nums.length) { // reach the end of array
                    for(int i=0; i<right-curr; i++) {
                        res.add(nums[curr]);
                    }
                    break;
                }else if (nums[curr] > nums[right]) {
                    for (int i=0; i < right-curr; i++) {
                        res.add(nums[curr]);
                    }
                    curr = right-1;
                }
            }

            left = nums[curr];
            curr=right;
            right = curr+1;
        }

        if(left < nums[curr]) res.add(nums[curr]);

        return res.toArray(new Integer[res.size()]);
    }
}
