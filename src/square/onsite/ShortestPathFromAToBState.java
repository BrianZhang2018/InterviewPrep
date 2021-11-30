package square.onsite;

import java.util.*;

/**
 * 面经: https://www.1point3acres.com/bbs/thread-739421-1-1.html
 *      给一个表有每个州旁边是什么州，自己建Graph，求A到B最短路径，BFS
 *
 * e.g. https://www.geeksforgeeks.org/building-an-undirected-graph-and-finding-shortest-path-using-dictionaries-in-python/
 *
 * Undirected graph
 *
 * Created by brianzhang on 4/1/21.
 */
public class ShortestPathFromAToBState {

    public static void main(String[] args) {
        String[][] stateNeighbors = {
                {"A", "B"}, {"A", "E"},
                {"A", "C"}, {"B", "D"},
                {"B", "E"}, {"C", "F"},
                {"C", "G"}, {"D", "E"}};
        System.out.println(solution(stateNeighbors, "A", "D"));
    }

    public static String solution(String[][] stateNeighbors, String A, String B){
        // build non-directed graph
        Map<String, List<String>> map = new HashMap<>();
        for(String[] state : stateNeighbors) {
            map.putIfAbsent(state[0], new ArrayList<>());
            map.putIfAbsent(state[1], new ArrayList<>());
            map.get(state[0]).add(state[1]);
            map.get(state[1]).add(state[0]);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(A);
        List<String> visited = new ArrayList<>();
        visited.add(A);

        // BFS find the shortest path
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                String path = queue.poll();
                String lastState = path.substring(path.length()-1);
                for(String state : map.get(lastState)){
                    if(visited.contains(state)) continue;

                    if(state.equals(B)) return path + state;

                    visited.add(state);
                    queue.add(path + state);
                    //System.out.println(path+state);
                }
            }
        }

        return null;
    }
}
