package compass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 8/10/20.
 */
public class Practise {

    public List<int[]> findRectanglePos(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res.add(new int[]{i, j});
                    helper(matrix, i, j, res);
                    return res;
                }
            }
        }

        return res;
    }

    public void helper(int[][] matrix, int r, int c, List<int[]> res) {

        outerLoop:
        for (int i = r; i < matrix.length; i++) {
            for (int j = c; j < matrix[0].length; i++) {
                if (matrix[i][j] == 1) {
                    if (i == matrix.length - 1 || matrix[i + 1][c] == 1) {
                        res.add(new int[]{i, j});
                        break outerLoop;
                    }
                    break;
                }

                matrix[i][j] = 1;
            }
        }
    }
}
