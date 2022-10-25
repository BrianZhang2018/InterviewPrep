package companies.wayfair;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-548370-1-1.html
 */
public class FindNoParentAndOneParentNodes {
    public static void main(String[] args) {
        int[][] parentChildPairs = new int[][] { { 11, 10 }, { 11, 12 }, { 10, 2 }, { 10, 5 }, { 1, 3 }, { 2, 3 },
                { 3, 4 }, { 5, 6 }, { 5, 7 }, { 7, 8 } };
        // List<Integer> res = findNoParentAndOneParentNodes(parentChildPairs);
        // for (int i : res) {
        //     System.out.println(i);
        // }

        System.out.println(hasCommonAncestor(parentChildPairs, 4, 12));
    }

    //without "the number of nodes" as input parameters
    //reverse the graph: son -> parent
    public static List<Integer> findNoParentAndOneParentNodes(int[][] pairs) {

        Set<Integer> res = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] i : pairs) {
            Set<Integer> set = map.getOrDefault(i[1], new HashSet<>());
            set.add(i[0]);
            map.put(i[1], set);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                res.add(entry.getKey());
            }
        }

        for (int[] i : pairs) {
            if (!map.containsKey(i[0])) {
                res.add(i[0]);
            }
        }
        return new ArrayList<>(res);
    }
    
    //dfsHelper + reversed graph to find the common descendant
    public static boolean hasCommonAncestor(int[][] pairs, int n1, int n2) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] i : pairs) {
            //reversed graph: son -> parent
            Set<Integer> set = graph.getOrDefault(i[1], new HashSet<>());
            set.add(i[0]);
            graph.put(i[1], set);
        }
        
        Set<Integer> set1 = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(n1);

        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            if (graph.containsKey(curr)) {
                for(int i : graph.get(curr)){
                    stack.add(i);
                    set1.add(i);
                }
            }
        }

        stack.add(n2);
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            if (graph.containsKey(curr)) {
                for(int i : graph.get(curr)){
                    if (set1.contains(i)) {
                        return true;
                    }
                    stack.add(i);
                }
            }
        }

        return false;
    }
}
