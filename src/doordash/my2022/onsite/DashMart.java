package doordash.my2022.onsite;

import java.util.*;

public class DashMart {
    public static void main(String[] args) {
        System.out.println((char)(2 + '0'));
        char[][] locationBoard = {
                {'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'},
                {'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'},
                {' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '},
                {' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
                {' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'}};
        int[][] locations = new int[][]{{2,2}, {4,0}, {0,4},{2,6}};
        DashMart dashMart = new DashMart();
        List<Integer> res = dashMart.findNearestDashMart(locationBoard, locations);
        System.out.println(res.toString());
    }

    public List<Integer> findNearestDashMart(char[][] matrix, int[][] locations) {
        List<Integer> res = new ArrayList<>();
        for(int[] l : locations) {
            res.add(bfs(matrix, l));
        }
        return res;
    }

    public int bfs(char[][] matrix, int[] location) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(location);
        int step = 1; int m = matrix.length, n = matrix[0].length;
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        Set<String> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] l = queue.poll();
                for(int[] dir : dirs) {
                    int nx = dir[0] + l[0];
                    int ny = dir[1] + l[1];
                    if(nx >=0 && nx < m && ny >=0 && ny <n && matrix[nx][ny] != 'X' && visited.add(nx+":"+ny)) {
                        if(matrix[nx][ny] == 'D') {
                            return step;
                        }
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
            step++;
        }

        return step;
    }
}
