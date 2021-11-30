package discovery;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Binary Search for Rotated array
 *
 * The idea is if it's a rotating the array, there must be one half of the array which is still in sorted order.
 * Created by brianzhang on 2/27/19.
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
        System.out.println(test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return mid;

            if(nums[start] <= nums[mid]){ // left part is sorted
                if(target >= nums[start] && target < nums[mid]) // target is in sorted part
                    end = mid - 1;
                else
                    start = mid + 1;
            }else {  // right part is sorted
                if(target > nums[mid] && target <= nums[end]) // target is in sorted part
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }

        return -1;
    }
}
