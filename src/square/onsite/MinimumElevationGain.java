package square.onsite;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-738713-1-1.html
 *
 * dfsHelper + backtracking
 * Created by brianzhang on 4/4/21.
 */
public class MinimumElevationGain {
    public static void main(String[] args) {
        int matrix[][] = {
                {1, 3, 6, 2, 5},
                {2, 3, 3, 4, 1},
                {1, 2, 5, 1, 1}};

        System.out.println(new MinimumElevationGain().solution(matrix));
    }

    private int min = Integer.MAX_VALUE;
    private String resPath = "";
    private int[][] dirs = new int[][]{{1,0}, {0,1}};

    public int solution(int[][] matrix){
        List<String> path = new ArrayList<>();
        path.add("0,0");
        dfs(matrix, 0, 0, matrix[0][0], 0, path);
        System.out.println(resPath);
        return min;
    }

    // dfsHelper + backtracking
    public void dfs(int[][] matrix, int i, int j, int preElevation, int elevationGain, List<String> path) {
        if(matrix[i][j] > preElevation && path.size() != 1) { // path.size()==1 means 初始状态，skip
            elevationGain += matrix[i][j] - preElevation;
        }

        if(i == matrix.length -1 && j == matrix[0].length -1) {
            if(min > elevationGain){
                min = elevationGain;
                resPath = Arrays.toString(path.toArray());
            }
            return;
        }

        for(int[] dir : dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];

            if(nr<0 || nr>= matrix.length || nc<0 || nc>=matrix[0].length || path.contains(nr+","+nc)) continue;
            // "path.contains(nr+","+nc)": path can be used for visited check since it record the coordinate, we actually can remove it as won't cause a visited cycle as we only move right and down

            path.add(nr+","+nc);
            dfs(matrix, nr, nc, matrix[i][j], elevationGain, path);
            path.remove(nr+","+nc);
        }
    }
}
