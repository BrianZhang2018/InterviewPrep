package companies.Karat;

import java.util.*;

/**
 * Suppose we have some input data describing a graph of relationships between parents and children over multiple generations.
 * The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.
 *
 * Intuition: 建立一个 son -> parent 的adjacent map to solve the issue related with parent (ancestor)
 *
 * https://gitee.com/aaalucard/carla/blob/master/wayfair_ancestors.go
 *
 * Created by brianzhang on 12/28/19.
 */
public class FindCommonAncestor {
    public static void main(String[] args) {
        int[][] parentChildPairs = new int[][] { {11, 10}, { 11, 12 }, { 10, 2 }, { 10, 5 }, { 1, 3 }, { 2, 3 },
                { 3, 4 }, { 5, 6 }, { 5, 7 }, { 7, 8 } };
        System.out.println(hasCommonAncestor(parentChildPairs, 4, 12));
    }

    // dfs + reversed graph to find the common descendant
    public static boolean hasCommonAncestor(int[][] pairs, int n1, int n2) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // map: key->son, value->parent which opposite with the input (parent, child) pairs
        for (int[] i : pairs) {
            Set<Integer> set = graph.getOrDefault(i[1], new HashSet<>());
            set.add(i[0]);
            graph.put(i[1], set);
        }

        Set<Integer> parentSetForN1 = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(n1);

        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            if (graph.containsKey(curr)) {
                for(int i : graph.get(curr)){
                    stack.add(i);
                    parentSetForN1.add(i);
                }
            }
        }

        stack.add(n2);
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            if (graph.containsKey(curr)) {
                for(int i : graph.get(curr)){
                    if (parentSetForN1.contains(i)) {
                        return true;
                    }
                    stack.add(i);
                }
            }
        }

        return false;
    }
}
