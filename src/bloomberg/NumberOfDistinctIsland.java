package bloomberg;

import java.util.*;

/**
 * https://www.lintcode.com/problem/number-of-distinct-islands/
 *
 *  Time complexity: O(n^2)
 *  Space complexity: O(n^2)
 * Created by brianzhang on 5/9/20.
 */
public class NumberOfDistinctIsland {

    public static void main(String[] args) {}

    public int numDistinctIslands(int[][] grid) {
        Set<String> res = new HashSet();

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 's', sb);
                    // System.out.println(sb.toString());
                    res.add(sb.toString());
                }
            }
        }

        return res.size();
    }

    public void dfs(int[][] grid, int i, int j, char c, StringBuilder sb) {
        if(i <0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        sb.append(c);

        // track dfs 的轨迹，从而达到知道小岛的形状
        dfs(grid, i-1, j, 'l', sb);
        dfs(grid, i, j+1, 'u', sb);
        dfs(grid, i+1, j, 'r', sb);
        dfs(grid, i, j-1, 'd', sb);

        sb.append('b');
    }
}
