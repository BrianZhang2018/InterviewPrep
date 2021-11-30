package square.onsite;

/**
 * https://www.1point3acres.com/bbs/thread-745252-1-1.html
 *
 * https://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-direction/
 *
 * The minimum cost is 65
 * The minimum cost can be obtained by first going to station 1 from 0.
 * Then from station 1 to station 3.
 *
 * Created by brianzhang on 4/10/21.
 */
public class MinCostTakeTrain {
    public static void main(String[] args) {
        int cost[][] = {{0, 15, 80, 90},
                        {INF, 0, 40, 50},
                        {INF, INF, 0, 70},
                        {INF, INF, INF, 0}};
        System.out.println(minCost(cost, 0, cost.length - 1));
    }

    static int INF = Integer.MAX_VALUE;

    public static int minCost(int[][] cost, int start, int end) {

        if (start == end || start + 1 == end) {
            return cost[start][end];
        }

        int min = cost[start][end];

        for (int i = start + 1; i < end; i++) {
            int c = minCost(cost, start, i) + minCost(cost, i, end);

            if (c < min) min = c;
        }

        return min;
    }
}
