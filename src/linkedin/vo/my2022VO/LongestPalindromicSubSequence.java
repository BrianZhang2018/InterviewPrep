package linkedin.vo.my2022VO;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Created by brianzhang on 8/12/18.
 */
public class LongestPalindromicSubSequence {
    public static void main(String[] args) {
        System.out.println(getLongestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromeSubseq("cbbd"));
    }

    // Solution - 1: DP bottom-top
    public static int getLongestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;

        int sl = s.length();
        int[][] dp = new int[sl][sl];

        for(int i=0; i<sl; i++) { // base case
            dp[i][i] =1;
        }
        // 要点：模板方法对于这类 palindrome 问题，e.g. MinimumInsertionStepsToMakeAPalindrome.java
        for(int l=2; l<=sl; l++){ // 问题规模
            for(int i=0, j=l-1; j<sl; i++, j++){ // i, j are pointer to compare the substring
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][sl-1];
    }

    // Solution-2: https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    // Top->bottom recursive method with memoization
    public static int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private static int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)  return 0; // invalid case
        if (i == j) return 1; // base case

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        }
        else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }

        return memo[i][j];
    }
}
