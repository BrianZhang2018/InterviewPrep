package qualtrics;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/flood-fill/
 *
 * Created by brianzhang on 1/17/21.
 */
public class FloodFill733 {

    // DFS
    int newColor = 0;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length ==0) return image;

        this.newColor = newColor;

        dfs(image, sr, sc, image[sr][sc]);

        return image;
    }

    public void dfs(int[][] image, int r, int c, int oldColor){

        if(r<0 || r>=image.length || c<0 || c>= image[0].length || image[r][c] == newColor || image[r][c] != oldColor) return;

        image[r][c] = newColor;

        dfs(image, r+1, c, oldColor);
        dfs(image, r, c+1, oldColor);
        dfs(image, r-1, c, oldColor);
        dfs(image, r, c-1, oldColor);
    }


    //BFS
    public int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length == 0) return image;

        LinkedList<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc});
        int old = image[sr][sc];
        image[sr][sc] = newColor;

        int[][] directions = new int[][]{{1,0}, {0,1}, {-1,0}, {0, -1}};
        while(!queue.isEmpty()){

            int[] node = queue.poll();

            for(int[] dir : directions){
                int nr = node[0] + dir[0];
                int nc = node[1] + dir[1];

                if(nr<0 || nr>=image.length || nc<0 || nc>=image[0].length || image[nr][nc] == newColor || image[nr][nc] != old) {
                    continue;
                }
                image[nr][nc] = newColor;
                queue.add(new int[]{nr, nc});
            }
        }

        return image;
    }
}
