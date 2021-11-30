package square.my2021;

import java.util.*;

/**
 * Created by brianzhang on 11/22/21.
 */
public class DetectCycleInDirectedGraph {
    public static void main(String[] args) {
        System.out.println(new DetectCycleInDirectedGraph().checkCycle(new String[][]{{"a", "b"},{"b", "c"}, {"c", "b"}}));
    }

    boolean result = false;

    public boolean checkCycle(String[][] nodeDeps) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String[] node : nodeDeps) {
            graph.putIfAbsent(node[0], new ArrayList<>());
            graph.get(node[0]).add(node[1]);
        }

        Set<String> visited = new HashSet<String>();
        String[] nodes = {"a", "b", "c"};
        for (int i = 0; i < nodes.length; i++){
            Set<String> path = new HashSet<String>();
            dfs(nodes[i], graph, path, visited);
        }
        return result;
    }

    // backtracking dfs
    public void dfs(String node, Map<String, List<String>> graph, Set<String> path, Set<String> visited) {
        List<String> adj = graph.get(node);
        if(adj == null) return;

        for(int i=0; i<adj.size(); i++) {
            String currNode = adj.get(i);
            if(!path.add(currNode)) {
                result = true;
                return;
            }else if(visited.add(currNode)) {
                dfs(currNode, graph, path, visited);
            }
            path.remove(currNode);
        }
    }
}
