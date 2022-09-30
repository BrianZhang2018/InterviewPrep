package crowdStrike;

import java.util.*;

/**
 * each minute, allow you only either buy One share or sell Any number of shares
 */
public class BestTimeBuyOneSellAnyStocks {
    public static void main(String[] args) {
 /*       System.out.println(maximizeProfit(new ArrayList<>(Arrays.asList(3,4,5,3,5,2))));
        System.out.println(maximizeProfit(new ArrayList<>(Arrays.asList(1,2,100))));*/
        System.out.println(maximizeProfit(new ArrayList<>(Arrays.asList(1,3,1,2))));
        System.out.println(maximizeProfit(new ArrayList<>(Arrays.asList(5,3,2,1))));
    }

    public static int maximizeProfit(List<Integer> prices) {
        int cost = 0, numOfShares = 0, profit = 0;
        for (int i = 0; i < prices.size()-1; i++) {
            if (prices.get(i + 1) > prices.get(i)) {
                cost += prices.get(i);
                numOfShares++;
            } else {
                if (numOfShares > 0) {
                    profit += prices.get(i) * numOfShares - cost;
                    cost = 0;
                    numOfShares = 0;
                }
            }
        }

        if(numOfShares > 0) {
            int soldOnLastDay = prices.get(prices.size()-1) * numOfShares - cost;
            return soldOnLastDay + profit > 0 ? soldOnLastDay + profit : 0;
        }

        return profit;
    }
}
