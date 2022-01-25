package apple.onsite2022;

public class FindNthMissingNum {
}

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/**
 * Given a list of sorted numbers, find the N-th number missing from the list.
 *
 *
 * Example 1:
 * List : {4,7,9,10}, K = 1
 * Result: 5
 * Explanation: The first missing number is 5.
 *
 * Example 2:
 * List : {4,7,9,10}, K = 3
 * Result: 8
 * Explanation:
 * The missing numbers are {5,6,8,...}, hence the third missing number is 8.
 *
 * Example 3:
 *
 * List : {1,2,4}, K = 3
 * Output: 6


 *  4,7,9,10
 l=4

 4,7
 l=2

 4
 l=1


 [4,5,7]


 1, define metric

 2. drill down issue

 3. monitor.




 Explanation:
 * The missing numbers are {3,5,6,7,...}, hence the third missing number is 6.
 *
 *
 */

class Solution {

    public static void main(String[] args) {
        System.out.println("Output :: " + missingNumber(new int[]{4,7,9,10}, 1));
        System.out.println("Output :: " + missingNumber(new int[]{4,7,9,10}, 3));
        System.out.println("Output :: " + missingNumber(new int[]{1,2,4}, 3));
        System.out.println("Output :: " + missingNumber(new int[]{4,7,9,10,12}, 4));
    }

    private static int missingNumber(int[] nums, int k) {
        if(nums == null && nums.length == 0) return -1;
        int l = 0; int r = nums.length-1;

        while(l<r) {

            int mid = l + (r-l)/2;
            int lg = (nums[mid]-nums[l]) - (mid-l);
            int rg =(nums[r]-nums[mid]) - (r-mid);

            if(lg > k) {
                r=mid;

            }else{
                l=mid;
            }

        }


        return -1;
    }

}
