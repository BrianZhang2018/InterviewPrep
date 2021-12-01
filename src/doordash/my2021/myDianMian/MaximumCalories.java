package doordash.my2021.myDianMian;

import java.util.*;

/**
 * knapsack problem - 拿或者不拿 - maximum gain
 *
 * coin change 变种
 * Created by brianzhang on 11/30/21.
 */
public class MaximumCalories {

    public static void main(String[] args) {
        System.out.println(maximumMenuCalories(new ArrayList<>(Arrays.asList(2.0, 1.0, 1.0)),
                new ArrayList<>(Arrays.asList(100, 10, 50)), 5.0));
    }

    // dp[p] (p is the spent amount): the maximum calories in when spent "p" amount
    public static int maximumMenuCalories(List<Double> prices, List<Integer> calories, Double budget) {
        int b = (int)Math.ceil(budget);
        int[] dp = new int[b+1];

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i=0; i<prices.size(); i++) {
            int p = (int)Math.floor(prices.get(i));
            for(int j=p; j<=b; j++) {
                if(dp[j-p] != Integer.MIN_VALUE) {
                    dp[j] = Integer.max(dp[j], dp[j-p] + calories.get(i));
                }
            }
        }

        return dp[b] == Integer.MIN_VALUE ? -1 : dp[b];
    }
}
