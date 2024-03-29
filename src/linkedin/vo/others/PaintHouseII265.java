package linkedin.vo.others;

/**
 * K colors (PaintHouseI only three colors), means compare with all colors for each house is very costly like PaintHouseI
 * so, we maintain a lastMin and lastSecondMin that we only compare it with next house.
 *
 * Created by brianzhang on 11/29/20.
 */
public class PaintHouseII265 {
    public static void main(String[] args) {
        System.out.println(minCostII(new int[][]{{14,2,11}, {11,14,5}, {14,3,10}}));
    }

    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int numOfColorOptions = costs[0].length;

        int lastMin = 0;
        int lastSecondMin = 0;
        int lastIndex = 0;
        for (int i = 0; i < costs.length; i++) {

            int currMin = Integer.MAX_VALUE;
            int currSecondMin = Integer.MAX_VALUE;
            int currIndex = -1;
            for (int j = 0; j < numOfColorOptions; j++) { // calculate min and secondMin with each color (and cost)
                costs[i][j] += (j != lastIndex ? lastMin : lastSecondMin); // "j" represent the color and cost, chose non-adjacent color
                if (costs[i][j] < currMin) {
                    currSecondMin = currMin;
                    currMin = costs[i][j];
                    currIndex = j;
                } else if (costs[i][j] < currSecondMin) {
                    currSecondMin = costs[i][j];
                }
            }
            // record last min and second min
            lastMin = currMin;
            lastSecondMin = currSecondMin;
            lastIndex = currIndex;
        }

        return lastMin;
    }
}
