package linkedin.vo;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * Created by brianzhang on 11/20/20.
 */
public class MaximumSumOfNonAdjacentElements {

    public int rob(int[] nums) {
        int rob = 0;
        int notRob = 0;

        for(int i=0; i<nums.length; i++){
            int currRob = notRob + nums[i];
            notRob = Math.max(notRob, rob);
            rob = currRob;
        }

        return Math.max(rob, notRob);
    }
}
