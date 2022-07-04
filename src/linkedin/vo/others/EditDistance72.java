package linkedin.vo.others;

/**
 * https://leetcode.com/problems/edit-distance/
 *
 * frequent: 5
 * Created by brianzhang on 11/17/20.
 */
public class EditDistance72 {

    // DP solution
    public int minDistanceDP(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=1; i<=m; i++) dp[i][0] = i;

        for(int j=1; j<=n; j++) dp[0][j] = j;

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else{
                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];
                    dp[i][j] = Math.min(replace, Math.min(insert, delete)) + 1;
                }
            }
        }

        return dp[m][n];
    }

    // Recursive + Memoization solution
    public int minDistanceTopDownMemoRecursive(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        return helper(word1, word2, m, n, new int[m][n]);
    }

    public int helper(String word1, String word2, int m, int n, int[][] memo){
        if(m==0) return n;

        if(n==0) return m;

        // Top-down, so look at m-1, n-1 character
        if(memo[m-1][n-1] != 0) return memo[m-1][n-1];

        if(word1.charAt(m-1) == word2.charAt(n-1)) helper(word1,word2, m-1, n-1, memo);

        int insert = helper(word1,word2, m, n-1, memo);
        int delete = helper(word1,word2, m-1, n, memo);
        int replace = helper(word1,word2, m-1, n-1, memo);

        memo[m-1][n-1] = Math.min(replace, Math.min(insert, delete));
        return memo[m-1][n-1];
    }
}
