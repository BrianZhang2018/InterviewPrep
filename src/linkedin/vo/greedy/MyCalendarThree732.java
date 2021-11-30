package linkedin.vo.greedy;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-iii/
 * 求有最多几个events同时overlap在某个时间上
 *
 * Solution: scan the timeline, https://zxi.mytechroad.com/blog/geometry/732-my-calendar-iii/
 * Created by brianzhang on 11/23/20.
 */
public class MyCalendarThree732 {
    public static void main(String[] args) {
        MyCalendarThree732 test = new MyCalendarThree732();
        test.book(10, 20); // returns 1
        test.book(50, 60); // returns 1
        test.book(10, 40); // returns 2
        test.book(5, 15); // returns 3
        test.book(5, 10); // returns 3
        test.book(25, 55); // returns 3
    }

    private TreeMap<Integer, Integer> tm;
    public MyCalendarThree732() {
        tm = new TreeMap();
    }

    public int book(int start, int end) {
        int ongoing = 0, k = 0;
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);

        for(int i : tm.values()){
            k = Math.max(k, ongoing += i);
        }

        return k;
    }
}
