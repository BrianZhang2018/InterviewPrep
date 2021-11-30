package postmate;

/**
 * https://leetcode.com/discuss/interview-question/439106/postmates-oa-2019-good-tuples
 *
 * Created by brianzhang on 6/19/20.
 */
public class GoodTuples {

    public static void main(String[] args) {
        System.out.println(getGoodTuples(new int[]{4,4,6,1,2,2,2,3}));
        System.out.println(getGoodTuples(new int[]{4,6,4,1,3,4}));
    }

    public static int getGoodTuples(int[] nums){

        int count = 0;
        for (int i = 1; i < nums.length -1; i++) {
            if ((nums[i-1] == nums[i] && nums[i] != nums[i+1])
                || (nums[i-1] != nums[i] && nums[i] == nums[i+1])
                || (nums[i-1]==nums[i+1] && nums[i-1]!=nums[i])
                || (nums[i-1]==nums[i+1]&& nums[i-1]!=nums[i])) {
                count++;
            }
        }
        return count;
    }
}
