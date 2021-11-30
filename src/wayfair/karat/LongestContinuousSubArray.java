package wayfair.karat;

import java.util.Arrays;

/**
 // [
 //   ["3234.html", "xys.html", "7hsaa.html"], // user1
 //   ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
 // ]
 // 						                user2
 // user1				    ""  "3234.html"		"sdhsfjdsh.html"	"xys.html"	"7hsaa.html"
 // 			""			0	    0				0					0			0
 // 		"3234.html"		0	    1				0					0			0
 // 		"xys.html"		0	    0				0					1			0
 // 		"7hsaa.html"	0	    0				0					0			2
 *
 * Created by brianzhang on 10/26/21.
 */
public class LongestContinuousSubArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
                new String[]{"3234.html", "xys.html", "7hsaa.html", "aaa"},
                new String[]{"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html", "aaa"})));
    }

    public static String[] solution(String[] history1, String[] history2) {
        int len1 = history1.length, len2 = history2.length;
        int[][] dp = new int[len1+1][len2+1];
        String[] res = new String[0];

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(history1[i-1].equals(history2[j-1])) {
                    dp[i][j] = dp[i-1][j-1] +1;
                    if(dp[i][j] > res.length) {
                        res = Arrays.copyOfRange(history1, i-dp[i][j], i);
                    }
                }
            }
        }

        return res;
    }
}
