package linkedin.vo.hard;

/**
 * https://leetcode.com/problems/count-different-palindromic-subsequences/
 *
 * huahua - https://www.youtube.com/watch?v=UjiFFYU3EKM
 * Created by brianzhang on 11/11/20.
 */
public class CountDifferentPalindromeSubsequenceDP730 {

    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
        System.out.println(countPalindromicSubsequences("bcbcb"));
        System.out.println(countPalindromicSubsequences("bbcabb"));
    }

    public static int countPalindromicSubsequences(String S) {
        int len = S.length();
        int[][] dp = new int[len][len];
        char[] charArr = S.toCharArray();
        int kMod = 1000000007;

        // 解题思路: 从长度为1的开始解，bottom up 最终解决长度为N的解

        // subProblem - length 1
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;
        }

        // subProblem - length start 2
        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;  // inclusive
                if(charArr[i] == charArr[j]){
                    dp[i][j] = dp[i+1][j-1]*2; // substring base (remove the left/rightmost character)

                    int l=i+1, r=j-1;
                    // Skip the same character, then will know how many same character in the substring
                    while(l <= r && charArr[l] != charArr[j]) l++; // can use charArr[i] instead
                    while(l <= r && charArr[r] != charArr[j]) r--;

                    if(l > r) dp[i][j] += 2; // substring doesn't includes any character which is same with left/rightmost character
                    else if(l == r) dp[i][j] += 1; // substring includes only "one" character which is same with left/rightmost character
                    else dp[i][j] -= dp[l+1][r-1]; // substring includes 2 or more character which are same with left/rightmost character
                }
                else{
                    dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1]; // 减是因为重复 (- dp[i+1][j-1])
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + kMod : dp[i][j] % kMod;
            }
        }

        return dp[0][len-1];
    }
}
