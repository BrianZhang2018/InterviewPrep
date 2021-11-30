package doordash.myVO;

import java.util.*;

/**
 * Topological sort
 *
 * Created by brianzhang on 9/3/20.
 */
public class DependencyPackage {
    // List<String> resolve(String target, Map<String, List<String>> dependencies)
    // {"A": ["B", "C", "D"],
    //  "B": ["C", "F"]
    //  "C": ["D"]}
    // resolve("A", dependencies) --> ["F", "C", "D", "B", "A"]
    public static void main(String[] args) {
        Map<String, List<String>> dependencies = new HashMap<>();
        dependencies.put("A", Arrays.asList("B", "C", "D"));
        dependencies.put("B", Arrays.asList("C", "F"));
        dependencies.put("C", Arrays.asList("D"));

        for (Map.Entry<String, List<String>> entry : helper("A", dependencies).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }

    public static Map<String, List<String>> helper(String target, Map<String, List<String>> dependencies) {
        HashMap<String, Integer> inDegreeMap = new HashMap<>();
        HashMap<String, String> adjacent = new HashMap<>();
        List<String> list = new ArrayList();
        list.add(target);

        List<String> bfs = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String p = list.get(i);
            List<String> deps = dependencies.get(p);
            inDegreeMap.put(p, deps.size());

            if (deps == null) continue;
            for (String str : deps) {
                if (dependencies.containsKey(str)) {
                    inDegreeMap.put(str, inDegreeMap.getOrDefault(str, 0) + dependencies.get(str).size());
                    adjacent.put(str, p);
                    list.add(str);
                } else {
                    inDegreeMap.put(str, 0);
                    if (!bfs.contains(str))
                        bfs.add(str);
                    adjacent.put(str, p);
                }
            }
        }

        for (int i = 0; i < bfs.size(); i++) {
            String dep = adjacent.get(bfs.get(i));
            int currInDegree = inDegreeMap.get(dep) - 1;
            if (currInDegree == 0) {
                if (!bfs.contains(dep))
                    bfs.add(dep);
            }
            inDegreeMap.put(dep, currInDegree);
        }

        Map<String, List<String>> res = new HashMap<>();
        res.put(target, bfs);

        return res;
    }
}

