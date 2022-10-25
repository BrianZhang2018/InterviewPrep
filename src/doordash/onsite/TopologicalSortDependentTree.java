package doordash.onsite;

import java.util.*;

/**
 * Time Complexity: O(V+E).
 *
 * Created by brianzhang on 8/25/20.
 */
public class TopologicalSortDependentTree {
    public static void main(String[] args) {
        System.out.println(findDependencyOrder(new String[][]{{"A", "B"}, {"B", "C"}, {"B", "D"}}, Arrays.asList("A", "B", "C", "D")));
        for (Map.Entry<String, List<String>> entry : findDependencyList(new String[][]{{"A", "B"}, {"B", "C"}, {"B", "D"}}, Arrays.asList("A", "B", "C", "D")).entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    public static String findDependencyOrder(String[][] input, List<String> nodes) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegreeMap = new HashMap<>();

        for (String node : nodes) inDegreeMap.put(node, 0);

        for (String[] i : input) {
            graph.putIfAbsent(i[1], new ArrayList<>());
            graph.get(i[1]).add(i[0]);
            inDegreeMap.put(i[0], inDegreeMap.getOrDefault(i[0], 0) + 1);
        }

        List<String> dependentList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) dependentList.add(entry.getKey());
        }

        for (int i = 0; i < dependentList.size(); i++) {
            List<String> deps = graph.get(dependentList.get(i));
            if (deps == null) break;

            for (int j = 0; j < deps.size(); j++) {
                String dep = deps.get(j);
                int currInDegree = inDegreeMap.get(dep) - 1;
                if (currInDegree == 0) {
                    dependentList.add(dep);
                }
                inDegreeMap.put(dep, currInDegree);
            }
        }

        if (dependentList.size() == nodes.size()) {
            return dependentList.toString();
        }

        return "";
    }

    public static Map<String, List<String>> findDependencyList(String[][] input, List<String> nodes) {
        HashMap<String, List<String>> map = new HashMap<>();
        HashMap<String, Integer> inDegreeMap = new HashMap<>();
        HashMap<String, List<String>> res = new HashMap<>();

        List<String> dependentList = new ArrayList<>();

        for (String node : nodes) inDegreeMap.put(node, 0);

        for (String[] i : input) {
            map.putIfAbsent(i[1], new ArrayList<>());
            map.get(i[1]).add(i[0]);
            inDegreeMap.put(i[0], inDegreeMap.getOrDefault(i[0], 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) dependentList.add(entry.getKey());
        }

        for (int i = 0; i < dependentList.size(); i++) {
            String currStart = dependentList.get(i);
            List<String> deps = map.get(currStart);
            if (deps == null) break;
            for (int j = 0; j < deps.size(); j++) {
                String dep = deps.get(j);
                res.putIfAbsent(dep, new ArrayList<>());
                res.get(dep).add(currStart);

                int currInDegree = inDegreeMap.get(dep) - 1;
                if (currInDegree == 0) {
                    dependentList.add(dep);
                }
                inDegreeMap.put(dep, currInDegree);
            }
        }

        return res;
    }
}

