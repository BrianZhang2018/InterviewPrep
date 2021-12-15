package linkedin.phoneScreening.ps2021.maximumSubArray;

public class MaximumProductSubarray {

   public int solution(int[] array) {
       if(array == null && array.length == 0) return -1;

       int l = 0, r = 0;
       int max = Integer.MIN_VALUE;

       for(int i=0; i<array.length; i++) {
           l = l==0 ? array[i] : l * array[i];
           r = r==0 ? array[array.length-i-1] : r * array[array.length-i-1]; // need for case: [3,-1,4]

           max = Math.max(max, Math.max(l,r));
       }

       return max;
   }
}
