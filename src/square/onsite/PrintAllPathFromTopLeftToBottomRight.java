package square.onsite;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/print-all-paths-from-top-left-to-bottom-right-in-a-matrix-with-four-moves-allowed/
 *
 * Print all paths from top left to bottom right in a matrix with four directions move allowed
 *
 * (find the all the solutionBFS in matrix - dfsHelper + backtracking)
 *
 * dfsHelper backtracking:
 *
 * 1. firstly, determine the valid directions, e.g. some question can be four directions, some just move right and down (two directions), this question is three directions
 *
 * 2. recursive dfsHelper+backtracking
 *
 * 3. use the visited to avoid the duplicate visit
 *
 * Created by brianzhang on 2/17/21.
 */
public class PrintAllPathFromTopLeftToBottomRight {

    public static void main(String[] args) {
        PrintAllPathFromTopLeftToBottomRight cp = new PrintAllPathFromTopLeftToBottomRight();
        System.out.println(cp.solution(new int[][]{ {1, 2},
                                                    {4, 5},
                                                    {3, 6}}));
        //System.out.println(dpSolution(3, 2));
    }

    private List<List<Integer>> allPath = new ArrayList<>();
    int[][] directions = new int[][]{{1,0}, {0,1}, {0,-1}}; // don't need {-1,0} here as move up is meaningless for this problem. 这道题告诉我们确定valid directions是backtracking首先要做的，并不是所有的direction都是valid的.

    // Solution-1: dfsHelper + backtracking
    public int solution(int[][] matrix) {
        List<Integer> path = new ArrayList<>();
        path.add(matrix[0][0]);
        dfs(matrix, 0, 0, path, new ArrayList<>());

        for(List l : allPath) System.out.println(l);
        return allPath.size();
    }

    public void dfs(int[][] matrix, int x, int y, List<Integer> path, List<String> visited) {
        visited.add(x + ":" + y);
        if(x == matrix.length-1 && y == matrix[0].length-1){
            allPath.add(new ArrayList<>(path));
            return;
        }

        for(int[] dir : directions){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= matrix.length || nx <0 || ny >= matrix[0].length || ny <0 || visited.contains(nx + ":" + ny))
                continue;

            path.add(matrix[nx][ny]);
            dfs(matrix, nx, ny, path, visited);
            visited.remove(nx + ":" + ny);
            path.remove(path.size()-1);
        }
    }

    // Solution-2: DP Solution
  /*  public static int dpSolution(int m, int n){
        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i ==0 || j==0){
                    dp[i][j] = 1;
                }else{
                    int tmp =0;
                    if(i+1<m || j+1<n){
                        tmp = (i+1)<m ? dp[i+1][j] : dp[i][j+1];
                    }else if(i+1<m && j+1<n){
                        tmp =  dp[i+1][j] + dp[i][j+1];
                    }

                    dp[i][j] = dp[i-1][j] + dp[i][j-1] + tmp;
                }
            }
        }

        return dp[m-1][n-1];
    }*/
}
