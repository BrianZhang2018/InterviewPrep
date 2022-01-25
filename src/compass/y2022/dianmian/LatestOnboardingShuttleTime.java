package compass.y2022.dianmian;

import java.util.*;

/**
 * 题不难，所以要多想edge cases
 * time complexity 答错了
 * Created by brianzhang on 11/8/21.
 */
public class LatestOnboardingShuttleTime {

    public static void main(String[] args) {
        String[] shuttles = {"10:00", "10:30", "11:00"}; // n
        String[] times = {"09:53", "09:57", "10:20", "10:25", "10:29", "10:50", "10:51", "10:54", "10:55"}; // m
        int capacity = 3; // k
        System.out.println(solution(shuttles, times, capacity));
    }

    // time complexity: O(n + m), space complexity: k (size of capacity)
    public static String solution(String[] s, String[] times, int c) {
        if(s == null || s.length ==0) return null;
        if(times == null || times.length == 0) return s[s.length -1];

        LinkedList<String> q = new LinkedList<>();

        int j= 0, i = 0;
        for(; i<s.length && j<times.length; i++) {  // time complexity: O(n + m), since the elements in "times" array which only loop once
            q.clear();
            while(j<times.length && q.size() < c && times[j].compareTo(s[i]) < 0) {
                q.add(times[j++]);
            }
        }

        String last = q.getLast();
        String[] strs = last.split(":");

        if(i< s.length) {
            return s[s.length -1];
        }

        if(q.size() < c) {

            return s[i-1];
        }

        if(strs[1].equals("00")) {
            int currHour = Integer.valueOf(strs[0]) - 1;
            return currHour + ":" + "59";
        }else{
            int mins = Integer.valueOf(strs[1]) - 1;
            if(mins == 0) {
                return strs[0] + ":00";
            }else if(mins < 10) {
                return strs[0] + ":0" + mins;
            } else {
                return strs[0] + ":" + mins;
            }
        }
    }
}
