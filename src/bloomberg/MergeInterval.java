package bloomberg;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * Followup question: InsertInterval
 *
 * Created by brianzhang on 5/10/20.
 */
public class MergeInterval {
    public static void main(String[] args) {
        MergeInterval mi = new MergeInterval();
        for(int[] res : mi.merge(new int[][]{{1,4}, {5,6}}))
            System.out.println(Arrays.toString(res));
    }

    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return intervals;

        //1. sort by first element in the array
        Arrays.sort(intervals, (s1, s2) -> Integer.compare(s1[0], s2[0])); // Comparator.comparingInt(s -> s[0])
        int[] ni = intervals[0];
        List<int[]> res = new ArrayList<>();
        res.add(ni);
        for(int[] i : intervals){
            if(i[0] <= ni[1]){ // has overlap
                ni[1] = Math.max(ni[1], i[1]);
            }else{  // no overlap
                ni = i;
                res.add(ni);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
