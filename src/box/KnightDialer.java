package box;

/**
 * https://leetcode.com/problems/knight-dialer/discuss/189271/Java-Top-Down-Memo-DP-O(N)
 *
 * Created by brianzhang on 10/2/20.
 */
public class KnightDialer {

    public static void main(String[] args) {}

    private static int mod = 1000000007;

    public int knightDialer(int n) {
        if(n == 1) return 10;

        int[][] graph = new int[][]{{4, 6}, {6,8}, {7,9}, {4, 8}, {3,9,0}, {}, {1,7,0}, {2,6}, {1,3}, {4, 2}};
        int[][] memo = new int[n][10];

        int cnt = 0;
        for(int i=0; i<=9; i++) {   // here is <=9
            cnt = (cnt + helper(n-1, i, memo, graph)) % mod;
        }

        return cnt;
    }

    public int helper(int n, int curr, int[][] memo, int[][] graph){
        if(n == 0) return 1;

        if(memo[n][curr] != 0) return memo[n][curr];

        int cnt = 0;
        for(int ng : graph[curr]){
            cnt = (cnt + helper(n-1, ng, memo, graph)) % mod;
        }

        memo[n][curr] = cnt;
        return cnt;
    }
}
