package datadog;

import java.util.*;

/**
 *
 * Created by brianzhang on 5/11/21.
 */
public class CountWords {

    public static void main(String[] args) {
        System.out.println(getNumOfDuplicateWords("This is a nice car, I would like to buy one. But I don't have enough money"));
        countAppearTimes("tag1,tag2,tag3,tag1");
    }

    public static int getNumOfDuplicateWords(String str){
        HashSet<String> hs = new HashSet<>();
        String[] strs = str.split(" ");
        int dup = 0;
        for(String s : strs) {
            if(!hs.add(s)) dup++;
        }

        return dup;
    }

    public static void countAppearTimes(String str){
        Map<String, Integer> map = new HashMap<>();
        String[] strs = str.split(",");

        for(String s : strs){
            map.put(s, map.getOrDefault(s, 0)  + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.add(entry);
        }

        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }
}


