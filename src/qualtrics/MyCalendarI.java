package qualtrics;

import java.util.*;
/**
 * new booking(newStart, newEnd)
 *
 * 1. check whether exist the interval which start time lower than newEnd
 * 2. if no, add it directly
 * 3. if yes, check whether the existing endTime greater than the newStart
 *
 * e.g.
 * new booking 5----10
 * exist booking 7----11 (overlap)
 *
 * new booking 12----100
 * exist booking 7----11 (non-overlap even though meet the above step-1, but fail on step-3)
 *
 * 解题关键: The logic of Conflict interval:
 * newEndTime > Prev existingStartTime
 * Prev existingEndTime > newStartTime
 *
 * Brute force and treeMap use the same above idea
 *
 * follow up 是如果一个时间最多允许两个会怎么办
 *
 * Created by brianzhang on 1/21/21.
 */
public class MyCalendarI {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(1,1);
        tm.put(2,2);
        tm.put(3,2);
        tm.put(10,10);
        System.out.println(tm.lowerKey(3)); // lower doesn't include itself

        for(Map.Entry<Integer, Integer> entry : tm.entrySet()){
            System.out.println(entry.getKey());
        }
    }

    TreeMap<Integer, Integer> map;
    public MyCalendarI() {
        map = new TreeMap<>();
    }
    // TreeMap solution
    public boolean bookTreeMap(int start, int end) {
        Integer low = map.lowerKey(end);

        if(low == null) {
            map.put(start, end);
            return true;
        }

        if(map.get(low) > start){
            return false;
        }

        map.put(start, end);

        return true;
    }

    // brute-force solution
    List<int[]> calendar = new ArrayList<>();

    public boolean book(int start, int end) {
        for (int[] i: calendar) {
            if (start < i[1] && i[0] < end) return false;
        }

        calendar.add(new int[]{start, end});
        return true;
    }
}
