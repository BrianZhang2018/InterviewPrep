package linkedin.phoneScreening;

import java.util.*;

/**
 * Adjacency matrix
 *
 * https://leetcode.com/problems/friend-circles
 * 读题很重要，便于理解这个 adjacency matrix 问题
 *
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 *
 * 解题思路：The idea is to BFS/DFS traverse each person's direct friend and indirect friend.
 *
 * Created by brianzhang on 11/2/20.
 */
public class FriendCircle {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(1,1);
        tm.put(3,3);
        tm.put(5,5);
        System.out.println(tm.ceilingKey(2));
        System.out.println(tm.floorKey(2));
    }
    // DFS
    // Time complexity: O(n^2). To complete matrix of size n^2 is traversed.
    public int findCircleNumDFS(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int cnt = 0;
        for(int i=0; i<M.length; i++){
            if(visited[i]) continue;

            if(!visited[i]){ // dfsHelper traverse the direct friends of "i" person
                visited[i] = true;
                dfs(M, i, visited);
                cnt++; // always count++ here as himself also is a group even though no other friends
            }
        }

        return cnt;
    }

    public void dfs(int[][] M, int i, boolean[] visited) {
        for(int j=0; j<M.length; j++){
            if(visited[j]) continue;

            if(M[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(M, j, visited); //  dfsHelper traverse the direct friends of "j" person which is the indirect friend of "i"
            }
        }
    }

    // BFS
    public int findCircleNumBFS(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }


    // UnionFind
    public int findCircleNumUnionFind(int[][] M) {

        int m = M.length, n = M[0].length;
        int[] root = new int[m];
        for(int i=0; i<m; i++) root[i] = i;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(M[i][j] == 1) unionFind(root, i, j);
            }
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) if (i == root[i]) cnt++;

        return cnt;
    }

    public void unionFind(int[] root, int i, int j){
        while(root[i] != i) i = root[i];   // find v1's root (can be improved with flat binaryTree)
        while(root[j] != j) j = root[j];   // find v2's root

        if(root[i] != root[j]) root[i] = j;  // unite the 2 subtrees
    }


}
