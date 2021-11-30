package grubhub.problems;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * TC: N*logN
 * Created by brianzhang on 10/16/19.
 */
public class TopKFrequentElement {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();

        for(int i : nums) map.put(i, map.getOrDefault(i, 0)+1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        pq.addAll(map.keySet());

        while(pq.size() >k){
            pq.poll();
        }

        int[] res = new int[k];

        for(int i=0; i<k; i++){
            res[i] = pq.poll();
        }

        return res;
    }
}
