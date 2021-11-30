package wayfair;

/**
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/319439/Java-DP-bottom-up-(2D-matrix)
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/312757/JavaPython-3-O(mn)-clean-DP-code-w-picture-comments-and-analysis.
 *
 * Dong note:
 * 解题思路: the path to get the length of LCS is the shortest common superSequence.
 * so, we firstly get the LCS, then bottom-up to get the reverse string of shortest common superSequence, then reverse it again to get final result
 *
 *
 * While coming back, see if top or left value is equal to mem[i][j]
    !the reason is this is how to construct the path when find the LCS, now just track back! - Dong note
 a. If equal to top, then add the character at i-1 of str1, and do i--
 b. If equal to left, then add the character at j-1 of str2, and do j--
 c. If none are equal, then add either of the characters, as they will be the same anyway and do i-- as well as j--

 * Time & space:: O(m * n), where m = s1.length(), n = s2.length().
 *
 * Created by brianzhang on 10/31/19.
 */
public class ShortestCommonSuperSequence {

    public static void main(String[] args) {
        System.out.println(solution("abac", "cab"));
    }

    public static String solution(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] mem = new int[n1+1][n2+1];
        int i, j;

        //find LCS
        for(i=1; i<=n1; i++) {   // i<=n, 注意
            for(j=1; j<=n2; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    mem[i][j] = 1 + mem[i-1][j-1];
                } else {
                    mem[i][j] = Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }
        i=n1;
        j=n2;
        StringBuilder str = new StringBuilder();
        while(i>0 && j>0) {
            if(mem[i][j] == mem[i-1][j]) {
                str.append(str1.charAt(i-1));
                i--;
            } else if(mem[i][j] == mem[i][j-1]) {
                str.append(str2.charAt(j-1));
                j--;
            } else {
                str.append(str1.charAt(i-1));
                i--;
                j--;
            }
        }
        while(i-- > 0) {
            str.append(str1.charAt(i));
        }
        while(j-- > 0) {
            str.append(str2.charAt(j));
        }
        return str.reverse().toString();
    }
}
