package linkedin.phoneScreening.ps2021.shortestWordDistance;

import java.util.*;

public class ShortestWordDistanceII {

    Map<String, List<Integer>> map = new HashMap();

    public ShortestWordDistanceII(String[] wordsDict) {
        for(int i=0; i<wordsDict.length; i++) {
            map.putIfAbsent(wordsDict[i], new ArrayList());
            map.get(wordsDict[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        int mini = Integer.MAX_VALUE;
        List<Integer> dis_1 = map.get(word1);
        List<Integer> dis_2 = map.get(word2);

        int i=0, j=0;
        while(i<dis_1.size() && j<dis_2.size()) { // merge sort idea
            mini = Math.min(mini, Math.abs(dis_1.get(i)- dis_2.get(j)));
            if(dis_1.get(i) < dis_2.get(j)) {
                i++;
            }else{
                j++;
            }
        }

        return mini;
    }
}
