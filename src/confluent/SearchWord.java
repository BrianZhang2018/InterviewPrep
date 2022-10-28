package confluent;

import java.util.*;
/**
 * Trie problem
 *
    given a list of doc id and text,
        (1) return the doc id of given word
        (2) return the doc id of given phrase
        E.g.
        [[1, confluent is a cloud computing company], [2, seattle is the new hub for cloud computing]]
        search(cloud) => returns [1, 2]
        search(confluent is a) => returns 1
        search(cloud computing) => returns [1, 2]
 */
public class SearchWord {
    public static void main(String[] args) {
/*        System.out.println(search("is",
                new String[]{"confluent is a cloud computing company",
                        "confluent is the new hub for cloud computing"}));
        System.out.println(search("is a",
                new String[]{"confluent is a cloud computing company",
                        "seattle is the new hub for cloud computing"}));*/
        System.out.println(search("cloud computing",
                new String[]{"confluent is a cloud computing company",
                        "seattle is the new hub for cloud computing"}));
    }

    public static List<Integer> search(String target, String[] phrases) {
        Trie root = new Trie("");
        // build trie
        for(int i=0; i<phrases.length; i++) {
            String[] words = phrases[i].trim().split(" ");
            insertIntoTrie(words, root, i);
        }
        String[] words = target.trim().split(" ");
        //Set<Integer> res = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        dfsSearch(words, 0, root, new ArrayList(), res);
        return res;
    }

    public static void dfsSearch(String[] words, int index, Trie node, List<String> list, List<Integer> res) {
        if(node == null) return;
        if(index == words.length) {
           res.addAll(node.indexes);
           return;
        }
        String word = words[index];
        if(node.map.containsKey(word)) {
            list.add(word);
            dfsSearch(words, index+1, node.map.get(word), list, res);
        } else {
            if(node.map.size() == 0) return;
            list.clear(); // clear since we need consecutive words (phrase)
            for(Map.Entry<String, Trie> entry : node.map.entrySet()) {
                dfsSearch(words, 0, entry.getValue(), list, res);
            }
        }
    }

    public static void insertIntoTrie(String[] words, Trie root, int index) {
        Trie node = root;
        for (String word : words) {
            if (!node.map.containsKey(word)) {
                node.map.put(word, new Trie(word));
            }
            node = node.map.get(word);
            node.indexes.add(index); // if indexes more than one, means prefix same
        }
        node.isPhraseEnd = true;
    }

    static class Trie {
        public String val;
        public List<Integer> indexes = new ArrayList<>();
        public Map<String, Trie> map = new HashMap<>();
        boolean isPhraseEnd = false;
        public Trie(String value) {
            this.val = value;
        }
    }
}
