package microsoft.ms2020.vo.intervalProblems;

import java.util.*;

/**
 * https://leetcode.com/problems/meeting-scheduler/
 *
 * https://www.1point3acres.com/bbs/thread-700482-1-1.html
 * Created by brianzhang on 1/10/21.
 */
public class MeetingSchedule1229 {

    // tow pointer
    // Time: O(NlogN) for Sorting, Two pointer only take O(M+N). Space: O(1)
    public List<Integer> minAvailableDuration1(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a,b)->a[0]-b[0]); // sort increasing by start time
        Arrays.sort(slots2, (a,b)->a[0]-b[0]); // sort increasing by start time

        int i = 0, j = 0;
        int n1 = slots1.length, n2 = slots2.length;
        while (i < n1 && j < n2) {
            // Find intersect between slots1[i] and slots2[j]
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);

            if (start + duration <= end) // Found the result
                return Arrays.asList(start, start + duration);
            else if (slots1[i][1] < slots2[j][1]) // 解题思路: compare the endTime so that we can move next to get a larger range of interval which has larger endTime
                i++;
            else
                j++;
        }
        return new ArrayList<>();
    }

    // brute force
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        Arrays.sort(slots1, (s1, s2)-> s1[0]-s2[0]);
        Arrays.sort(slots2, (s1, s2)-> s1[0]-s2[0]);

        for(int[] s1 : slots1){
            for(int[] s2 : slots2){
                if(s1[1] <= s2[0]) break; // slots2 sorted by start time, so if the s1 endtime < s2 start time, should break

                int intersectionStart = Math.max(s2[0], s1[0]);
                int intersectionEnd = Math.min(s2[1],s1[1]);

                if(intersectionStart+duration <= intersectionEnd){
                    return Arrays.asList(intersectionStart, intersectionStart + duration);
                }
            }
        }

        return new ArrayList<>();
    }
}
