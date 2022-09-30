package square.oa2022;

import java.util.Arrays;

public class ReferenceCellSum {

    public static void main(String[] args) {
        String[][] input = new String[][]{{"1", "A1+1", "A1+2"}}; // A1 means row 0 col 1
        System.out.println(Arrays.deepToString(helper(input)));
    }

    public static int[][] helper(String[][] input) {
        int[][] res = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                res[i][j] = dfs(input, input[i][j]);
            }
        }

        return res;
    }

    public static int dfs(String[][] input, String expr) {
        String[] strs = expr.split("\\+");
        int sum = 0;
        for (String str : strs) {
            if (isNumeric(str)) {
                sum += Integer.valueOf(str);
            } else {
                int row = str.charAt(0) - 'A';
                int col = str.charAt(1) - '0' - 1;
                if (isNumeric(input[row][col])) {
                    sum += Integer.valueOf(input[row][col]);
                } else {
                    sum += dfs(input, str);
                }
            }
        }

        return sum;
    }

    public static boolean isNumeric(String str) {
        // match a number with optional '-' and decimal.
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
