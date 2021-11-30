package bloomberg;

/**
 * Frequency: 2
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Follow up question: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * Created by brianzhang on 5/7/20.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {}

    public static int search1(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target) return mid;

            if(nums[mid] >= nums[l]){
                if(target >= nums[l] && target < nums[mid]){
                    r = mid-1;
                }else{
                    l = mid +1;
                }
            }else if(nums[mid] <=nums[l]){
                if(target > nums[mid] && target <= nums[r]){
                    l = mid+1;
                }else{
                    r = mid -1;
                }
            }
        }
        return -1;
    }

    // array has duplicate number, e.g. [1,0,1,1,1]
    public static boolean search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r-l) / 2;

            if (target == nums[mid]) return true;

            if (nums[mid] > nums[l]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            } else if(nums[mid] < nums[l]) {
                if (target > nums[mid] && target <=nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                l++;
            }

        }
        return false;
    }
}
