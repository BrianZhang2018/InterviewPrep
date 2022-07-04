package linkedin.vo.my2022VO.interval;
import java.util.*;
/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * Created by brianzhang on 1/15/19.
 */
public class MergeInterval {
    public static void main(String[] args) {
        Interval v1 = new Interval(1, 3), v2 = new Interval(2, 6), v3 = new Interval(8, 10);
        for (Interval i : merge(Arrays.asList(v1, v2, v3))) System.out.println(i.start + " - " + i.end);
    }

    // Solution-1: my refactored solution
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);

        for (int i=1; i < intervals.size(); i++) {
            // template step: find overlap and merge
            if (intervals.get(i-1).end >= intervals.get(i).start) {
                intervals.get(i).end = Math.max(intervals.get(i - 1).end, intervals.get(i).end);
                intervals.get(i).start = intervals.get(i - 1).start;
            } else {
                res.add(intervals.get(i-1)); // add to result when non-overlap
            }
        }

        res.add(intervals.get(intervals.size()-1));
        return res;
    }


    // Solution-2: foreach loop for fun
    public static List<Interval> merge2(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;

        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        Interval temp = new Interval(Integer.MAX_VALUE, Integer.MIN_VALUE);

        for (Interval i : intervals) {
            if (temp.start == Integer.MAX_VALUE || temp.end >= i.start) {
                temp.end = Math.max(temp.end, i.end);
                temp.start = Math.min(temp.start, i.start);
            } else {
                res.add(temp);
                temp = i;
            }
        }

        res.add(temp);
        return res;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
