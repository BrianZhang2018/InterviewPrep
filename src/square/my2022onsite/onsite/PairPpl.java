package square.my2022onsite.onsite;

import java.util.*;

// ["alex", "bob", "cindy", "brian"] -> [[alex, bob], [cindy, brian]]
// pair the people they never met before
// [[alex, bob], [cindy, brian]]
// [[alex, cindy], [bob, cindy]]
// [[alex, brian], [bob, brian]]
/**
 * Combination problem
 *
 * note: name the variable meaningful to avoid the confusing in coding
 */
public class PairPpl {
    public static void main(String[] args) {
        PairPpl test = new PairPpl();
        System.out.println(Arrays.deepToString(test.helper(new String[]{"alex", "bob",
                "cindy", "brian"}).toArray()));
        System.out.println(Arrays.deepToString(test.helper(new String[]{"alex", "bob",
                "cindy", "brian"}).toArray()));
        System.out.println(Arrays.deepToString(test.helper(new String[]{"alex", "bob",
                "cindy", "brian"}).toArray()));
    }

    List<String> memo = new ArrayList<>();
    public List<String[]> helper(String[] input) {
        List<String> used = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (used.contains(input[i])) continue;
            for (int j = i + 1; j < input.length; j++) {
                if (used.contains(input[j])) continue;
                String from = input[i], to = input[j];
                if (!memo.contains(from+to) && !memo.contains(to+":"+from)) {
                    used.add(from);
                    used.add(to);
                    memo.add(from+":"+to); // common tricky: simplified the data structure with string
                    memo.add(to+":"+from);
                    break;
                }
            }
        }

        List<String[]> res = new ArrayList<>();
        for(int i=0, j=1; j<used.size(); i+=2, j+=2) {
            res.add(new String[] {used.get(i), used.get(j)});
        }
        return res;
    }

    // combination solution with map memo
    Map<String, List<String>> map = new HashMap<>();
    public List<String[]> helper1(String[] input) {
        List<String[]> res = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (visited.contains(input[i])) continue;
            for (int j = i + 1; j < input.length; j++) { // combination skip the item which used before, don't need to look back like permutation
                if (visited.contains(input[i])) continue;
                if (map.size() == 0 || map.get(input[i]) == null || !map.get(input[i]).contains(input[j])) {
                    String from = input[i], to = input[j];
                    res.add(new String[]{from, to});
                    map.putIfAbsent(from, new ArrayList<>());
                    map.get(from).add(to);
                    map.putIfAbsent(to, new ArrayList<>());
                    map.get(to).add(from);

                    visited.addAll(Arrays.asList(from, to));
                    break;
                }
            }
        }

        return res;
    }

    // bad solution with permutation way
    public List<String[]> helper2(String[] input) {
        List<String[]> res = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        for (String from : input) {
            if (visited.contains(from)) continue;
            for (String to : input) { // permutation that look back
                if(from.equals(to)) continue;
                if (visited.contains(to)) continue;
                if (map.size() == 0 || map.get(from) == null || !map.get(from).contains(to)) {
                    res.add(new String[]{from, to});
                    List fromList = map.getOrDefault(from, new ArrayList<>());
                    fromList.add(to);
                    map.put(from, fromList);

                    List toList = map.getOrDefault(to, new ArrayList<>());
                    toList.add(from);
                    map.put(to, toList);

                    visited.add(from);
                    visited.add(to);
                    break;
                }
            }
        }

        return res;
    }
}
