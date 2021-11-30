package doordash;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=567730&page=1&extra=#pid12442396
 *
 * Created by brianzhang on 7/21/20.
 */
public class MeetingSchedule {

    public static void main(String[] args) {

        int[][] input = {{3, 20}, {-2, 0}, {0, 2}, {16, 17}, {19, 23}, {30, 40}, {27, 33}};
        for (int[] i : minAvailableDuration(input, -5, 27, 2))
            System.out.println(Arrays.toString(i));

        System.out.println(Arrays.toString(meetingSchedule1229(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 8)));
    }

    public static List<int[]> minAvailableDuration(int[][] slots, int startTime, int endTime, int duration) {
        Arrays.sort(slots, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i][0] >= startTime && slots[i][0] <= endTime) {
                if (i == 0 && (startTime < 0 ? Math.abs(startTime - slots[i][0]) : startTime - slots[i][0]) >= duration + 1)
                    res.add(new int[]{startTime, slots[i][0]});
                else if ((slots[i][0] < 0 ? Math.abs(slots[i][0] - slots[i - 1][1]) : slots[i][0] - slots[i - 1][1]) >= duration + 1) {
                    res.add(new int[]{slots[i - 1][1], slots[i][0]});
                }
            }
        }

        return res;
    }


    public static int[] meetingSchedule1229(int[][] slots1, int[][] slots2, int duration) {

        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int index1 = 0, index2 = 0;
        while (index1 < slots1.length && index2 < slots2.length) {
            int[] s1 = slots1[index1];
            int[] s2 = slots2[index2];

            if (s1[0] > s2[1]) {
                index2++;
            } else if (s1[1] < s2[0]) {
                index1++;
            } else {
                // get intersection part
                int ms = Math.max(s1[0], s2[0]);
                int me = Math.min(s1[1], s2[1]);

                if (me - ms >= duration) {
                    return new int[]{ms, ms + duration};
                } else if (s1[1] < s2[1]) {
                    index1++;
                } else {
                    index2++;
                }

            }
        }
        return new int[]{};
    }
}
