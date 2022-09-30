package square.my2022onsite;

import java.util.*;

/**
 * similar with word search problem
 *
 * TC: O(M*N*4^L) - M*N is the size of the board, L is the length of the word (因为每一个character都可以走4个方向在matrix里, so 4^L)
 * SC: O(L), L is the length of the word to be matched
 */
public class GetAllPath {
    public static void main(String[] args) {
        GetAllPath test = new GetAllPath();
        for(String str: test.getAllPath(new int[5][5], new int[]{0,1}, new int[]{2,1}))
            System.out.println(str);
    }

    enum Direction {
        L(0, -1), R(0, 1), D(1, 0), U(-1, 0);
        int row, col;
        Direction(int r, int c) {
            this.row = r;
            this.col =c;
        }
    }

    public List<String> getAllPath(int[][] grid, int[] start, int[] target) {
        List<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        dfs(grid, start, null, target,  new StringBuilder(), res, visited);
        return res;
    }

    // backtracking
    public void dfs(int[][] grid, int[] start, Direction direction, int[] target, StringBuilder sb, List<String> res, boolean[][] visited) {
        int row = start[0], col = start[1];
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col]) return;

        if(direction != null) sb.append(direction.name());

        if(Arrays.equals(start,target)) {
            res.add(sb.toString());
        }

        visited[row][col] = true;
        dfs(grid, new int[]{start[0] + Direction.L.row, start[1] + Direction.L.col}, Direction.L, target, sb, res, visited);
        dfs(grid, new int[]{start[0] + Direction.R.row, start[1] + Direction.R.col}, Direction.R, target, sb, res, visited);
        dfs(grid, new int[]{start[0] + Direction.D.row, start[1] + Direction.D.col}, Direction.D, target, sb, res, visited);
        dfs(grid, new int[]{start[0] + Direction.U.row, start[1] + Direction.U.col}, Direction.U, target, sb, res, visited);
        visited[row][col] = false;
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
