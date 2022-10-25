package doordash.my2022;

import java.util.Arrays;
/**
 * https://leetcode.com/problems/next-permutation/discuss/13872/Easiest-JAVA-Solution
 * https://www.youtube.com/watch?v=quAS1iydq7U
 *
 * 找下一个更大的数
 *
 * Created by brianzhang on 2/23/20.
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation test = new NextPermutation();
        int[] input = new int[]{1,2,3};
        test.nextPermutation(input);
       // System.out.println(Arrays.toString(input));
        System.out.println(test.nextNegative("-45323"));
    }

    public void nextPermutation(int[] A) {
        if(A == null || A.length <= 1) return;
        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);  // Reverse the descending sequence
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }

// if input is negative number, find next larger number
    private String nextNegative(String n) {
        char[] nums = n.toCharArray();
        int i = nums.length - 2;
        while (i > 0 && nums[i] <= nums[i + 1]) i--; // Find 1st id i that breaks ascending order
        if (i <= 0) return n;

        int j = nums.length-1;
        while (j > 0 && nums[j] >= nums[i]) j--; // Find rightmost first smaller id j

        swap1(nums, i, j);
       // System.out.println(nums);
        StringBuilder sb = new StringBuilder(new String(Arrays.copyOfRange(nums, i+1, nums.length))).reverse();
        sb.insert(0, new String(Arrays.copyOfRange(nums, 0, i+1)));
        return sb.toString();
    }

    public void swap1(char[] A, int i, int j) {
        char tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
