package linkedin.phoneScreening.highF;

import java.util.*;

/**
 * https://leetcode.jp/problemdetail.php?id=244
 *
 * https://leetcode.com/problems/shortest-word-distance-ii/
 *
 * Created by brianzhang on 10/25/20.
 */
public class ShortestWordDistanceII244 {

    public static void main(String[] args) {
        ShortestWordDistanceII244 swd = new ShortestWordDistanceII244(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(swd.shortest("coding", "practice"));
    }

    private Map<String, ArrayList<Integer>> map =new HashMap<>();

    public ShortestWordDistanceII244(String[] words) {
        for(int i=0; i<words.length; i++){
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1), l2 = map.get(word2);

        int i=0, j=0, min = Integer.MAX_VALUE;
        while(i<l1.size() && j<l2.size()){
            min = Math.min(min, Math.abs(l1.get(i) - l2.get(j)));
            if(l1.get(i) < l2.get(j)){
                i++;
            }else{
                j++;
            }
        }
        return min;
    }
}
