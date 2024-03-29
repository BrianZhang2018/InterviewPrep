package linkedin.vo.hard;

/**
 * http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-730-count-different-palindromic-subsequences/
 *
 * Time complexity: O(n^2)
 * Space complexity: O(n^2)
 *
 * Created by brianzhang on 11/20/20.
 */
public class MemoRecursive {

    public static void main(String[] args) {
        MemoRecursive mr = new MemoRecursive();
        System.out.println(mr.countPalindromicSubsequences("bccb"));
    }

    private int[][] memo;
    private static final int kMod = 1000000007;
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        memo = new int[n][n];
        return count(S.toCharArray(), 0, n - 1);
    }

    private int count(char[] s, int i, int j) {
        if (i > j) return 0;
        if (i == j) return 1;

        if (memo[i][j] > 0) return memo[i][j];

        long ans = 0;

        if (s[i] == s[j]) {
            ans += count(s, i + 1, j - 1) * 2;
            int l = i + 1;
            int r = j - 1;

            while (l <= r && s[l] != s[i]) ++l;
            while (l <= r && s[r] != s[i]) --r;

            if (l > r) ans += 2;
            else if (l == r) ans += 1;
            else ans -= count(s, l + 1, r - 1);

        } else {
            ans = count(s, i, j - 1) + count(s, i + 1, j) - count(s, i + 1, j - 1);
        }

        memo[i][j] = (int)((ans + kMod) % kMod);
        return memo[i][j];
    }
}
