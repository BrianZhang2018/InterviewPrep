package liverramp;

/**
 * https://www.hackerrank.com/challenges/minimum-swaps-2/problem?isFullScreen=true
 *
 * Created by brianzhang on 8/16/20.
 */
public class MinimumSwap {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,3,4,1,5}));
    }

    // Complete the minimumSwaps function below.
    static int solution(int[] arr) {

        int count = 0;

        for(int i=1; i<=arr.length; i++){
            if(i!=arr[i-1]){
                int t = i+1;
                while(t<arr.length && i!=arr[t-1]){
                    t++;
                }
                count++;
                int temp = arr[i-1];
                arr[i-1] = arr[t-1];
                arr[t-1] = temp;
            }
        }

        return count;

    }
}
