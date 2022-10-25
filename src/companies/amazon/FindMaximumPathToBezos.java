package companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.1point3acres.com/bbs/thread-646144-1-1.html
 * 第二题
   给你一个weighted directed graph代表整个亚麻的reporting chain，weight是每个reportee给上司汇报的“delay”，求整个公司maximum total
   delay from any employee to Jeff Bezos。如果有多个可能的reporting chain是选最短的那条。
 *
 *
 * 对于每个员工选最短 对于所有员工选最长
     A员工有8和10两条路径
     B员工有6和12两条路径
     A选8
     B选6
     max delay over all是8
 * Created by brianzhang on 6/28/20.
 */
public class FindMaximumPathToBezos {

    public static void main(String[] args) {
        FindMaximumPathToBezos test = new FindMaximumPathToBezos();
        int[][] times = new int[][]{{1,2,1}, {2,1,1}, {1,3,2}, {2,4,2}, {4,3,1}, {1, 5, 4}, {5, 3, 4}, {2,3,1}};
        System.out.println("result is: " + test.networkDelayTime(times, 1, 3));
    }

    int max = Integer.MIN_VALUE;

    public int networkDelayTime(int[][] times, int start, int target) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for(int[] i : times){
            map.putIfAbsent(i[0], new HashMap<>());
            map.get(i[0]).put(i[1], i[2]);
        }

        if(map.get(start) == null) return -1;

        boolean[] memo = new boolean[times.length+1];

        for(Map.Entry<Integer, Integer> entry : map.get(start).entrySet()){
            memo[start] = true;
            int pathWeight = dfs(entry, entry.getValue(), map, memo, target);
            System.out.println(pathWeight);
            max = Math.max(pathWeight, max);
        }

        return max;
    }

    public int dfs(Map.Entry<Integer, Integer> entry, int count, Map<Integer, Map<Integer, Integer>> map, boolean[] memo, int target){

        if(entry.getKey() == target) return count;

        if(map.get(entry.getKey()) == null) return 0;

        int sum = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> e : map.get(entry.getKey()).entrySet()){
            if(!memo[e.getKey()]){
                memo[e.getKey()] = true;
                count += e.getValue();
                sum = Math.min(sum, dfs(e, count, map, memo, target));
                memo[e.getKey()] = false;
            }
        }

        return sum;
    }

}
