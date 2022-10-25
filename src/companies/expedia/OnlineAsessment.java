package companies.expedia;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OnlineAsessment {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
       /* InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        if (line == null) {
            return;
        }*/

        String line = "banana";
        int n = line.length();
        int[][] dp = new int[n + 1][n + 1];

        int maxLength = 0;
        int index = 0;

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (line.charAt(i - 1) == line.charAt(j - 1) && dp[i - 1][j - 1] < j - i) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        index = Math.max(i, index);
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        String res = maxLength > 0 ? line.substring(index - maxLength, index) : "";
        System.out.println(res);

        int[] a = new int[]{2, 3};

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
                new Comparator<Integer>(){
                    public int compare(Integer a, Integer b){
                        return a - b;
                    }
                }
        );

    }
}