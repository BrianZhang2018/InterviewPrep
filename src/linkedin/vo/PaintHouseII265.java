package linkedin.vo;

/**
 * k colors, so compare with all colors for each house is very costly like PaintHouseI
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

        int lastMin = 0, lastSecondMin = 0, lastIndex = 0;
        for (int i = 0; i < costs.length; i++) {

            int currMin = Integer.MAX_VALUE, currSecondMin = Integer.MAX_VALUE, currIndex = -1;
            for (int j = 0; j < costs[0].length; j++) { // calculate min and secondMin with each color (and cost)
                costs[i][j] += (j != lastIndex ? lastMin : lastSecondMin); // j represent the color and cost, chose non-adjacent color
                if (costs[i][j] < currMin) {
                    currSecondMin = currMin;
                    currMin = costs[i][j];
                    currIndex = j;
                }
                else if (costs[i][j] < currSecondMin) {
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
