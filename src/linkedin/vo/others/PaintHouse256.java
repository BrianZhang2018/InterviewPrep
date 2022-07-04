package linkedin.vo.others;

/**
 * https://www.lintcode.com/problem/paint-house/my-submissions
 *  no two adjacent houses have the same color
 *
 * f : 2
 * leetcode 256, 265
 * Created by brianzhang on 11/20/20.
 */
public class PaintHouse256 {

    public static void main(String[] args) {
        System.out.println(minCostI(new int[][]{{14,2,11}, {11,14,5}, {14,3,10}}));
    }

    public static int minCostI(int[][] costs){
        if(costs == null || costs.length == 0) return 0;

        int n = costs.length;
        for(int i=1; i<n; i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]); // "i" compare with "i-1" (front house)
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][1], costs[i-1][0]);
        }

        return Math.min(costs[n-1][0], Math.min(costs[n-1][1], costs[n-1][2]));
    }
}
