package qualtrics;

import java.util.*;

/**
 * https://leetcode.com/problems/employee-free-time/
 * Use Merge Interval solution to solve this problem (MergeInterval.java)
 *
 * 1. Merge interval
 * 2. Collect the Gap time
 *
 * freq: 1
 *
 * Created by brianzhang on 8/2/20.
 */
public class EmployeeFreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> freeIntervals = new ArrayList<>(); // result
        List<Interval> busyIntervals = new ArrayList<>();

        for (List<Interval> l : schedule) busyIntervals.addAll(l);
        // sort by start time - step-1
        Collections.sort(busyIntervals, (a, b) -> a.start - b.start);

        for (int i = 1; i < busyIntervals.size(); i++) {
            Interval prev = busyIntervals.get(i - 1);
            Interval curr = busyIntervals.get(i);
            // check the overlap and merge the interval - step-2
            if (curr.start <= prev.end) {
                curr.end = Math.max(curr.end, prev.end);
                curr.start = prev.start;
            } else {
                freeIntervals.add(new Interval(prev.end, curr.start));
            }
        }
        return freeIntervals;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

