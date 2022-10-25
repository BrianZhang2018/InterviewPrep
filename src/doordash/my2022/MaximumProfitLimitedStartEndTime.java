package doordash.my2022;

import java.util.*;

/**
 You're a dasher, and you want to try planning out your schedule. You can view a list of deliveries along with their associated start time, end time, and dollar amount for completing the order. Assuming dashers can only deliver one order at a time, determine the maximum amount of money you can make from the given deliveries.

 The inputs are as follows:

 int start_time: when you plan to start your schedule
 int end_time: when you plan to end your schedule
 int d_starts[n]: the start times of each delivery[i]
 int d_ends[n]: the end times of each delivery[i]
 int d_pays[n]: the pay for each delivery[i]
 The output should be an integer representing the maximum amount of money you can make by forming a schedule with the given deliveries.

 Constraints
 -----------
 end_time >= start_time
 d_ends[i] >= d_starts[i]
 d_pays[i] > 0
 len(d_starts) == len(d_ends) == len(d_pays)

 Example
 -------
 start_time = 0
 end_time = 10
 d_starts = [2, 3, 5, 7]
 d_ends = [6, 5, 10, 11]
 d_pays = [5, 2, 4, 1]

 Expected: 6
 */
class TestData {
    int startTime;
    int endTime;
    int[] dStarts;
    int[] dEnds;
    int[] dPays;
    int expect;
    TestData(int startTime, int endTime, int[] dStarts, int[] dEnds, int[] dPays, int expect) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dStarts = dStarts;
        this.dEnds = dEnds;
        this.dPays = dPays;
        this.expect = expect;
    }
}

public class MaximumProfitLimitedStartEndTime {
    public static int getMaxPay(int startTime, int endTime, int[] dStarts, int[] dEnds, int[] dPays) {
        int n =dStarts.length;
        int[][] schedule = new int[n][];
        for(int i=0; i<n; i++) {
            schedule[i] = new int[] {dStarts[i], dEnds[i], dPays[i]};
        }
        Arrays.sort(schedule, (a,b)-> (a[1]-b[1]));
        TreeMap<Integer, Integer> tm = new TreeMap();
        tm.put(0, 0);

        for(int[] s : schedule) {
            // don't put the schedule into the treemap if people can't do it
            if(s[0] < startTime) continue;
            if(s[1] > endTime) break;

            int profit = tm.floorEntry(s[0]).getValue() + s[2];
            if(profit > tm.lastEntry().getValue()) {
                tm.put(s[1], profit);
            }
        }

        return tm.lastEntry().getValue();
    }

    public static void main(String[] args) {
        TestData[] testData = new TestData[] {
                new TestData(
                        0,
                        10,
                        new int[]{2, 3, 5, 7},
                        new int[]{6, 5, 10, 11},
                        new int[]{5, 2, 4, 1},
                        6
                ),
                new TestData(
                        1,
                        10,
                        new int[]{2, 2, 2, 4},
                        new int[]{3, 3, 3, 5},
                        new int[]{2, 5, 2, 4},
                        9
                ),
                new TestData(
                        1,
                        10,
                        new int[]{2, 4, 4, 4},
                        new int[]{3, 5, 5, 5},
                        new int[]{4, 2, 5, 2},
                        9
                ),
                new TestData(
                        1,
                        10,
                        new int[]{1, 5, 7, 0},
                        new int[]{11, 15, 12, 3},
                        new int[]{1, 1, 1, 1},
                        0
                ),
        };

        for(int i = 0; i < testData.length; i++) {
            TestData td = testData[i];
            int result = getMaxPay(td.startTime, td.endTime, td.dStarts, td.dEnds, td.dPays);
            if (result == td.expect) {
                System.out.println("-- Test " + (i+1) + ": PASSED --");
            } else {
                System.out.println("-- Test " + (i+1) + ": FAILED --");
                System.out.println("  Expected: " + td.expect + ", got: " + result + "\n");
            }
        }
    }
}