package linkedin.vo.myVO;

import java.util.*;

/**
 * Created by brianzhang on 12/1/20.
 */
public class WordLadderWithPreCalculatedNeighborsGraph {
    // LEAD -> GOLD
    // LEAD -> LOAD -> GOAD -> GOLD

    // APE -> MAN
    // APE -> APT -> OPT -> OAT -> MAT -> MAN

    // APE, MAN

    // calculate(englishDictionary, "APE", "MAN") -> ["APE", "APT", "OPT", "OAT", "MAT", "MAN"]

    // n: Number of words
    // m: Length of the input word
    // s: Number of letters in alphabet
    //
    // O(n * m * s)
    //
    // AP* -> [APE, APT, ... ]
    // *PE -> [...]
    // A*E -> [..]
    // O*T -> [
    // OA* ->

    // O(n * m * )

    // CAT
    // *AT, C*T, CA*
    // *OG, D*G, DO*
    public static void main(String[] args) {
        WordLadderWithPreCalculatedNeighborsGraph test = new WordLadderWithPreCalculatedNeighborsGraph();
        System.out.println(test.calculate(new HashSet(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")),"hit", "cog"));
    }

    int calculate(Set<String> dict, String start, String end) {
        Queue<String> queue = new LinkedList();
        queue.add(start);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String w = queue.poll();
                Map<String, List<String>> edgeMap = createEdgeMap(dict, w);
                Set<String> edgeWords = findEdge(edgeMap, w);
                if (edgeWords != null) {
                    for (String edgeWord : edgeWords) {
                        if(edgeWord.equals(end))
                            return count;

                        if(!edgeWord.equals(w)) queue.add(edgeWord);
                    }
                } else {
                   continue;
                }
            }
            count++;
        }

        return count;
    }

    Set<String> findEdge(Map<String, List<String>> precomputeMap, String word) {
        Set<String> res = new HashSet();
        for (int i = 0; i < word.length(); i++) {
            List<String> collection = precomputeMap.get(word.substring(0, i) + "*" + word.substring(i + 1));
            if(collection != null) res.addAll(collection);
        }
        return res;
    }

    // PreCompute
    Map<String, List<String>> createEdgeMap(Set<String> dict, String input) {
        Map<String, List<String>> map = new HashMap();
        // APE
        // *PE -> [APE]
        // A*E -> [APE]
        // AP* -> [APE]
        for (String word : dict) {
            if (!word.equals(input) && (word.length() == input.length())) {
                for (int i = 0; i < word.length(); i++) {
                    map.putIfAbsent(word.substring(0, i) + "*" + word.substring(i + 1), new ArrayList());
                    map.get(word.substring(0, i) + "*" + word.substring(i + 1)).add(word);
                }
            }
        }
        return map;
    }
}
