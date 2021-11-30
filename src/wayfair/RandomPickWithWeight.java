package wayfair;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
 *
 * Created by brianzhang on 11/3/19.
 */
public class RandomPickWithWeight {

    public static void main(String[] args) {
        RandomPickWithWeight test = new RandomPickWithWeight(new int[]{1,3});
        System.out.println(test.pickIndex());
    }

    Random random = new Random();
    int[] wSum;

    public RandomPickWithWeight(int[] w) {
        //accumulate the the array to get a proportion range for each index
        for(int i=1; i< w.length; i++){
            w[i] = w[i] + w[i-1];
        }
        this.wSum = w;
    }

    public int pickIndex() {
        int idx = random.nextInt(wSum[wSum.length-1]) + 1;
        int left =0, right = wSum.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;
            if(wSum[mid] == idx) return mid;

            if(idx<wSum[mid]){
                right = mid;
            }else{
                left = mid +1;
            }
        }

        return left;
    }
}
