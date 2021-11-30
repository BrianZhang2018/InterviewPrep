package postmate;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * <p>
 * Created by brianzhang on 7/18/20.
 */
public class DiagonalSort {

    // Driven Code
    public static void main(String[] args) {
        int a[][] = {
                {1, 2, 3, 9},
                {4, 5, 6, 1},
                {7, 8, 9, 8},
                {1, 3, 9, 1},};
        printAntiDiagonal(a, 4);
    }

    static void printAntiDiagonal(int[][] matrix, int size) {

        for (int i = 0; i < size; i++) {
            int row = 0, col = i;
            while (row >= 0 && col >= 0) {
                System.out.println(matrix[row][col]);
                row++;
                col--;
            }
        }

        for (int j = 1; j < size; j++) {
            // traversing downwards starting from last column
            int col = size - 1, row = j;
            while (row < size) {
                System.out.println(matrix[row][col]);
                row++;
                col--;
            }
        }
    }
}
