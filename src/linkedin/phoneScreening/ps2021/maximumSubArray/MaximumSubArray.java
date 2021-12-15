package linkedin.phoneScreening.ps2021.maximumSubArray;

public class MaximumSubArray {
    public int solution(int[] array) {
        int prefixSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i : array) {
            if(prefixSum < 0) prefixSum = 0;

            prefixSum += i;
            max = Math.max(max, prefixSum);

        }

        return max;
    }
}
