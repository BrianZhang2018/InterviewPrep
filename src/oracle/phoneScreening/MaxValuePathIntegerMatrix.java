package oracle.phoneScreening;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Created by brianzhang on 10/4/20.
 */
public class MaxValuePathIntegerMatrix {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0,1,0},{1,3,6},{4,5,1}}));
        System.out.println(dpSolution(new int[][]{{0,1,0},{1,3,6},{4,5,1}}));
    }

    public static int solution(int[][] matrix){
        return dfs(matrix, 0, 0, 0);
    }

    public static int dfs(int[][] matrix, int r, int c, int value){

        if(r >= matrix.length || c >= matrix[0].length)
            return value;

        value += matrix[r][c];

        int right = dfs(matrix, r+1, c, value);
        int down = dfs(matrix,r, c+1, value);

        return right >= down ? right : down;
    }

    public static int dpSolution(int[][] grid){
        int m = grid.length, n = grid[0].length;

        for(int i = 0; i<m; i++){
            for(int j =0; j<n; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                if(i == 0){
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                }else if(j ==0){
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                }else{
                    grid[i][j] = Math.max(grid[i][j-1], grid[i-1][j]) + grid[i][j];
                }
            }
        }

        return grid[m-1][n-1];
    }

}
