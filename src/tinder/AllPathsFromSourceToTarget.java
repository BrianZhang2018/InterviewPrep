package tinder;

import java.util.*;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 *
 * Bloomberg, tinder
 * Created by brianzhang on 6/9/20.
 */
public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {
      for(List<Integer> l : new AllPathsFromSourceToTarget().allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}))
          System.out.println(Arrays.toString(l.toArray()));
    }

    // DFS solution
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new ArrayList(Arrays.asList(0));
        dfs(graph, 0, path, graph.length-1);
        return res;
    }

    public void dfs(int[][] graph, int currNode, List<Integer> path, int targetNode) {
        if(currNode == targetNode) {
            res.add(new ArrayList(path));
            return;
        }

        for(Integer i: graph[currNode]){
            if(path.contains(i)) continue;

            path.add(i);
            dfs(graph, i, path, targetNode);
            path.remove(path.size() -1);
        }
    }

    // BFS solution
    public List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayDeque<List<Integer>> dq = new ArrayDeque<>();
        dq.add(Arrays.asList(0));

        int n = graph.length - 1; // the target node

        while(!dq.isEmpty()){
            List<Integer> path = dq.pop();
            if(path.get(path.size()-1) == n){
                res.add(path);
                continue;
            }

            for(int i: graph[path.get(path.size()-1)]){
                //create a new path list base on old one for each next node
                List<Integer> nl = new ArrayList(path);
                nl.add(i);
                dq.add(nl);
            }
        }

        return res;
    }

}
