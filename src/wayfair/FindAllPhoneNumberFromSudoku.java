package wayfair;

import java.util.ArrayList;
import java.util.List;

/**
 * backtracking
 * T: O(M * N * 4^L)
 * S: O(4mn)
 * https://stackoverflow.com/questions/51899275/find-all-combinations-in-a-3x3-matrix-following-some-rules
 *
 * Created by brianzhang on 10/31/19.
 */
public class FindAllPhoneNumberFromSudoku {

    static List<List<Integer>> output = new ArrayList<>();

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};

        FindAllPhoneNumberFromSudoku test = new FindAllPhoneNumberFromSudoku();
        test.solution(matrix);

        for(List<Integer> res : output){
            for(Integer r : res){
                System.out.print(r);
            }
            System.out.println();
        }
    }

    public void solution(int[][] matrix){

        if(matrix == null || matrix.length == 0)
            return;

        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                backtracking(matrix, i, j, new ArrayList<>(), new boolean[m][n]);
            }
        }

    }

    public void backtracking(int[][] matrix, int x, int y, List<Integer> res, boolean[][] memo){
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0,-1}, {0, 1}};

        if(res.size() == 5){
            output.add(new ArrayList<>(res));
            return;
        }

       for(int[] dir : dirs){
           int nx = dir[0] + x;
           int ny = dir[1] + y;

           if(nx<0 || nx >= matrix.length || ny <0 || ny >= matrix[0].length || memo[nx][ny]){
              continue;
           }

           res.add(matrix[nx][ny]);
           memo[nx][ny] = true;
           backtracking(matrix, nx, ny, res, memo);
           res.remove(res.size()-1);
           memo[nx][ny] = false;
       }
    }
}
