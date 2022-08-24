package doordash.my2021.dianMian;

import java.util.*;

/**
 * knapsack problem - 拿或者不拿 - maximum gain
 *
 * coin change 变种
 * Created by brianzhang on 11/30/21.
 */
public class MaximumCalories {

    public static void main(String[] args) {
        System.out.println(maximumMenuCalories(
                new ArrayList<>(Arrays.asList(2.0, 3.0, 1.0)), // prices
                new ArrayList<>(Arrays.asList(100, 100, 50)), // calories
                5.0)); // budget
    }

    // dp[p] (p is the spent amount): the maximum calories in when spent "p" amount
    public static int maximumMenuCalories(List<Double> prices, List<Integer> calories, Double budget) {
        int budget_int = (int) (budget*100);
        int[] dp = new int[budget_int+1];

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i=0; i<prices.size(); i++) {
            int price_int = (int)(prices.get(i) * 100);
            for(int j=price_int; j<=budget_int; j++) { // j is the current spent amount
                if(dp[j-price_int] != Integer.MIN_VALUE) {
                    dp[j] = Integer.max(dp[j], dp[j-price_int] + calories.get(i));
                }
            }
        }

        return dp[budget_int] == Integer.MIN_VALUE ? -1 : dp[budget_int];
    }

    // similar with leetcode maximum scheduling job
}
