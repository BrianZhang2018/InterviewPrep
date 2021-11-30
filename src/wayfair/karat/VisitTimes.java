package wayfair.karat;

import java.util.*;

/**
 * Created by brianzhang on 10/24/21.
 */
public class VisitTimes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[][] {{"John", "809"}, {"John", "810"}, {"John", "850"}, {"John", "853"},  {"John", "855"}}).toArray()));
    }
    public static List<String> solution(String[][] input) {
        List<String> res = new ArrayList<>();
        Map<String, TreeSet<String>> map = new HashMap<>();
        for(String[] s : input) {
            map.putIfAbsent(s[0], new TreeSet<>());
            map.get(s[0]).add(s[1]);
        }

        for(Map.Entry<String, TreeSet<String>> entry : map.entrySet()) {
            List<String> times = new ArrayList(entry.getValue());
            int l=0, r=2;
            while(times.size()>=3 && r<times.size()) {
                while(r<times.size() && (Integer.valueOf(times.get(r)) - Integer.valueOf(times.get(l)) <= 60)) {
                    r++;
                }

                if(r-l>2) {
                    res.addAll(times.subList(l, r));
                    break;
                }

                l++;
                r++;
            }
        }

        return res;
    }
}
