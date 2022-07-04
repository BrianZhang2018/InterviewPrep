package linkedin.vo.others;

import javafx.util.Pair;
import java.util.*;

/**
 * follow up: Return their position index sorted by their area.
 *
 * f : 3
 * Created by brianzhang on 11/12/20.
 */
public class NumberOfIslandAndSort200 {

    public static void main(String[] args) {
        //int[][] grid = {{1, 1}, {1, 0}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        NumberOfIslandAndSort200 test = new NumberOfIslandAndSort200();
        for(Pair<Integer, List<int[]>> p : test.maxAreaOfIsland(grid)){
            System.out.println(p.getKey());
            for (int[] i : p.getValue()) {
                System.out.println(Arrays.toString(i));
            }
        }
    }

    public PriorityQueue<Pair<Integer, List<int[]>>> maxAreaOfIsland(int[][] grid) {
        // Pair<area, locations>
        PriorityQueue<Pair<Integer, List<int[]>>> pq = new PriorityQueue<>((a,b) -> a.getKey()-b.getKey());
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<int[]> locations = new ArrayList();
                    int area = dfs(grid, i, j, locations);
                    pq.add(new Pair<>(area, locations));
                }
            }
        }
        return pq;
    }

    public int dfs(int[][] grid, int i, int j, List<int[]> locations) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 0) return 0;

        locations.add(new int[]{i, j});
        grid[i][j] = 0; // flip the number to mark as visited
        return 1 + dfs(grid, i + 1, j, locations) + dfs(grid, i, j + 1, locations) + dfs(grid, i - 1, j, locations) + dfs(grid, i, j - 1, locations);
    }
}
