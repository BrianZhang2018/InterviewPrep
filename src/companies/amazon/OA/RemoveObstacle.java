package companies.amazon.OA;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class RemoveObstacle{
    public static void main(String[] args){

        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1,1,1));
        res.add(Arrays.asList(1,0,1));
        res.add(Arrays.asList(1,1,9));

        System.out.println(new RemoveObstacle().removeObstacle(3, 3, res));
    }

    boolean[][] visited = null;
    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot) {
        if (numRows == 0 || numColumns == 0 || lot == null) {
            return -1;
        }

        visited = new boolean[numRows][numColumns];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        int step = 0;
        int[] deltax = new int[]{0, 1, 0, -1};
        int[] deltay = new int[]{1, 0, -1, 0};
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] current = queue.poll();
                if (lot.get(current[0]).get(current[1]) == 9) {
                    return step;
                }
                for (int diff = 0; diff < 4; diff++) {
                    int nextX = current[0] + deltax[diff];
                    int nextY = current[1] + deltay[diff];
                    if (isValiad(nextX, nextY, numRows, numColumns, lot, visited)) {
                        queue.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isValiad(int nextX, int nextY, int numRows, int numColumns, List<List<Integer>> lot, boolean[][] visited) {
        if (nextX < 0 || nextX >= numRows || nextY < 0 || nextY >= numColumns || lot.get(nextX).get(nextY) == 0 || visited[nextX][nextY]) {
            return false;
        }
        return true;
    }
}