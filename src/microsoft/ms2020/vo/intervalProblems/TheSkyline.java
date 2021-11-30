package microsoft.ms2020.vo.intervalProblems;

import java.util.*;

/**
 * https://leetcode.com/problems/the-skyline-problem/discuss/61193/Short-Java-solution
 *
 * https://www.youtube.com/watch?v=v5CMa5MUGCo&t=690s
 *
 * Created by brianzhang on 1/10/21.
 */
public class TheSkyline {

    public static void main(String[] args) {
        int[][] input = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        for(List<Integer> keyPoint : getSkyline(input)) System.out.println(Arrays.toString(keyPoint.toArray()));
    }

    // 解题思路:
    // 每当线移动的时候，key point 就出现了
    // entering point: 当有"新"的进入点的高度，意味着有一个新的building (线向上移动)
    // leaving point: 移除当前queue里的最高点，下降到一个"新"的高度 （线向下移动）
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList();
        List<int[]> heights = new ArrayList();

        for(int[] b: buildings){
            heights.add(new int[]{b[0], -b[2]}); // start of a building, height stored as negative
            heights.add(new int[]{b[1], b[2]}); // end of a building, height stored as positive
        }

        Collections.sort(heights, (a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0]; // X coordinate: sort from left to right
            else
                return a[1] - b[1]; // Since the entering height is negative, so the larger abs(height) will be on the front
        });

        // Max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        pq.offer(0);
        int prevMax = 0;

        for(int[] h : heights){
            if(h[1]<0){
                pq.offer(-h[1]); // h[1] < 0, that means it meets a new building, so add it to pq
            }else{
                pq.remove(h[1]); // h[1] >= 0, that means it has reached the end (right) of the building, so remove it from pq
            }

            int currMax = pq.peek();
            if(currMax != prevMax){ // if the max height is different from the previous one, that means a critical point is met, add to result list
                res.add(Arrays.asList(h[0], currMax));
                prevMax = currMax;
            }
        }

     /*  // But priority queue does not support remove in lgn time
        // treeMap support add, remove, get max in lgn time, so use treeMap here
        // key: height, value: number of this height
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, 1);
        // Before starting, the previous max height is 0;
        int prev = 0;
        // visit all points in order
        for (int[] h : heights) {
            // a start point, add height
            if (h[1] < 0) {
                tm.put(-h[1], tm.getOrDefault(-h[1], 0) + 1);
            } else {  // a end point, remove height
                if (tm.get(h[1]) > 1) {
                    tm.put(h[1], tm.get(h[1]) - 1);
                } else {
                    tm.remove(h[1]);
                }
            }
            int cur = tm.lastKey();
            // compare current max height with previous max height, updateHotNodes result and
            // previous max height if necessary
            if (cur != prev) {
                res.add(Arrays.asList(h[0], cur));
                prev = cur;
            }
        }*/
        return res;
    }
}
