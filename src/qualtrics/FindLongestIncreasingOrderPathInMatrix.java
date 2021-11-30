package qualtrics;

/**
 * https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 *
 * Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell)
 * such that all cells along the path are in increasing order with a difference of 1.
 *
 * backtracking + memo
 *
 * tc: m*n
 * Created by brianzhang on 1/16/21.
 */
public class FindLongestIncreasingOrderPathInMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 10, 9},
                                     {5, 11, 8},
                                     {4, 6,  7}};
        FindLongestIncreasingOrderPathInMatrix test = new FindLongestIncreasingOrderPathInMatrix();
        System.out.println(test.solution(matrix));
    }

    public int solution(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int longestPath = Integer.MIN_VALUE;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int res = dfs(matrix, i, j, -1, memo);
                longestPath = Math.max(longestPath, res);
                memo[i][j] = res;
            }
        }
        return longestPath;
    }

    public int dfs(int[][] matrix, int r, int c, int preNum, int[][] memo) {
        if(preNum != -1) {
            if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] - preNum != 1) return 0;
        }

        if(memo[r][c] != 0){
            return memo[r][c];
        }else{
            int res1 = dfs(matrix, r+1, c, matrix[r][c], memo);
            int res2 = dfs(matrix, r, c-1, matrix[r][c], memo);
            int res3 = dfs(matrix, r-1, c, matrix[r][c], memo);
            int res4 = dfs(matrix, r, c+1, matrix[r][c], memo);

            return 1 + Math.max(Math.max(res1, res2), Math.max(res3, res4));
        }
    }
}
