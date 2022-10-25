package companies.Karat;

import java.util.*;

/**
 * Intuition: 建立一个 son -> parent 的adjacent map to solve the issue related with parent (ancestor)
 * https://www.1point3acres.com/bbs/thread-548370-1-1.html
 */
public class FindNoParentAndOneParentNodes {
    public static void main(String[] args) {
        int[][] parentChildPairs = new int[][] { { 11, 10 }, { 11, 12 }, { 10, 2 }, { 10, 5 }, { 1, 3 }, { 2, 3 },
                { 3, 4 }, { 5, 6 }, { 5, 7 }, { 7, 8 } };
         List<Integer> res = findNoParentAndOneParentNodes(parentChildPairs);
         for (int i : res) System.out.println(i);
    }

    //without "the number of nodes" as input parameters
    //reversed group: son -> parent
    public static List<Integer> findNoParentAndOneParentNodes(int[][] pairs) {

        Set<Integer> res = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>(); //build an inbound adjacent map
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
}
