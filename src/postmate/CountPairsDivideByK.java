package postmate;

/**
 * https://www.geeksforgeeks.org/count-pairs-in-array-whose-sum-is-divisible-by-k/
 *
 * Created by brianzhang on 6/17/20.
 */
public class CountPairsDivideByK {

    public static void main(String[] args)
    {
        int A[] = { 2, 2, 1, 7, 5, 3, 0, 4};
        int K = 4;
        System.out.print(countKDividePairs(A, K));
    }

    public static int countKDividePairs(int[] nums, int K)
    {
        int[] freq = new int[K+1];

        int counter = 0;
        for(int i = 0; i<nums.length; i++) {

            int rem = nums[i] % K;
            if (rem != 0) {
                counter += freq[K-rem];
                freq[rem]++;
            }else{
                if(nums[i] == K && freq[0] > 0){
                    counter += freq[0];
                }
                if(nums[i] == 0 && freq[K] > 0){
                    counter += freq[K];
                }
                freq[nums[i]]++;
            }
        }

        return counter;
    }
}
