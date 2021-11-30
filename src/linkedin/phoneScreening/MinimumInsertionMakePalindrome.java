package linkedin.phoneScreening;

/**
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 *
 * //https://www.youtube.com/watch?v=XNWz9xbX8F0&t=5s
 *
 * Created by brianzhang on 11/9/20.
 */
public class MinimumInsertionMakePalindrome {

    public static void main(String[] args) {
        System.out.println(minInsertions("mbadm"));
    }

    public static int minInsertions(String s) {

        if(s == null || s.length() <=1) return 0;

        int n = s.length();
        int[][] dp = new int[n][n];

        for(int l=2; l<=n; l++){
            for(int i=0, j=l-1; j<n; i++, j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) +1;
            }
        }

        return dp[0][n-1];
    }
}
