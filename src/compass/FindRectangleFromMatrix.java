package compass;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-589325-1-1.html
 *
 * Created by brianzhang on 8/4/20.
 */
public class FindRectangleFromMatrix {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 0}};

        getRectangleLocation(matrix).forEach(list -> {
            for (int[] i : list) System.out.println(Arrays.toString(i));

            System.out.println("====================");
        });

        for (List<int[]> l : printAllZeroPosition(matrix)) {
            StringJoiner joiner = new StringJoiner(",");
            for (int[] i : l) joiner.add(Arrays.toString(i));
            System.out.println(joiner.toString() + "\n" + "==============");
        }

        for (int[] r : matrix) System.out.println(Arrays.toString(r));
    }

    public static List<List<int[]>> getRectangleLocation(int[][] matrix) {
        List<List<int[]>> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    List<int[]> temp = new ArrayList<>();
                    temp.add(0, new int[]{i, j});
                    findEndPosition(i, j, matrix, temp);
                    res.add(temp);
                }
            }
        }

        return res;
    }

    public static void findEndPosition(int r, int c, int[][] matrix, List<int[]> temp) {
        int m = matrix.length, n = matrix[0].length;

        outerLoop:
        for (int i = r; i < m; i++) {
            for (int j = c; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == m - 1 || matrix[i + 1][c] == 1) {
                        temp.add(new int[]{i, j - 1});
                        break outerLoop;
                    }

                    break;
                }

                matrix[i][j] = 1;
            }
        }
    }

    // question - 3
    public static List<List<int[]>> printAllZeroPosition(int[][] matrix) {

        List<List<int[]>> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    List<int[]> temp = new ArrayList<>();
                    dfs(i, j, matrix, temp);
                    res.add(temp);
                }
            }
        }

        return res;
    }

    public static void dfs(int r, int c, int[][] matrix, List<int[]> temp) {
        if (r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length || matrix[r][c] == 1 )
            return;

        temp.add(new int[]{r, c});
        matrix[r][c] = 1;

        dfs(r + 1, c, matrix, temp);
        dfs(r - 1, c, matrix, temp);
        dfs(r, c + 1, matrix, temp);
        dfs(r, c - 1, matrix, temp);
    }
}
