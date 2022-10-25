package companies.google;

import java.util.*;

/**
 * Similar question is CutOffTreeForGolfEvent
 * Created by brianzhang on 2/23/19.
 */
public class DiagonalTraverse {

    public static void main(String[] args) {
        int[][] test = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i : findDiagonalOrder(test)) {
            System.out.println(i);
        }
    }

    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[]{};

        int[][] dicts = {{0, 1}, {1, 0}};
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0});

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        int level = 1;
        int count = 0;

        while (!queue.isEmpty()) {
            int currSize = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < currSize; i++) {
                int[] curr = queue.poll();
                list.add(matrix[curr[0]][curr[1]]);

                for (int[] dict : dicts) {
                    int x = curr[0] + dict[0];
                    int y = curr[1] + dict[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y])
                        continue;
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }

            if (level % 2 != 0) {
                Collections.reverse(list);
            }
            for (Integer val : list) {
                res[count++] = val;
            }
            level++;
        }
        return res;
    }


    public int[] findDiagonalOrder1(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {                // moving down
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return arr;
    }
}
