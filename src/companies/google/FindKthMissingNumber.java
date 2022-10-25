package companies.google;

/**
 * 这题关键是找the range of kth missing number，而不是通常的binary search去直接找mid value
 * https://www.1point3acres.com/bbs/thread-525374-1-1.html
 */
public class FindKthMissingNumber{

    public static void main(String[] args){
        int[] testArr = new int[] {2, 4, 7, 8, 9, 15};
        FindKthMissingNumber test = new FindKthMissingNumber();
        System.out.println(test.findKthMissing(testArr, 2));
        System.out.println(10 + '9'-'0');
    }

    public int findKthMissing(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length-1;

        //find the range of missing number by binary search
        while(left + 1 < right){ //因为是要找一个range，漏掉的数一定是在给出的两个数之间。你要是用left<=right就不对了。
            
            int mid = left + (right-left)/2;
            int missingNums = nums[mid] - nums[left] - (mid-left);

            if(missingNums >= k){
                right = mid;
            }else if(missingNums < k){
                left = mid;
                k -= missingNums;
            }
        }

       return nums[left] + k > nums[right] ? -1 : nums[left] + k;

    }

}