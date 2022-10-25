package companies.oracle;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Created by brianzhang on 3/1/20.
 */
public class BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockII test = new BestTimeToBuyAndSellStockII();
        System.out.println(test.maxProfit(new int[]{7,1,5,3,6,4}));
    }

    // Greedy algorithm
    public int maxProfit(int[] prices) {

        if(prices == null || prices.length == 0)
            return 0;

        int total = 0;
        for(int i=0; i<prices.length-1; i++){
            if(prices[i+1] > prices[i])
                total+= prices[i+1] - prices[i];
        }

        return total;
    }

    // Peak Valley Approach
    public int maxProfit1(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}
