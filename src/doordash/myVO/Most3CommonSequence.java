package doordash.myVO;

import java.util.*;

/**
 * Created by brianzhang on 9/3/20.
 */
public class Most3CommonSequence {

    public static void main(String[] args) {
        String[][] input = new String[][]{{"U1", "Homepage"}, {"U2", "Something"}, {"U1", "OrderHistory"}, {"U1", "Reorder"}, {"U2", "OrderHistory"}, {"U3", "Homepage"}, {"U1", "Settings"}, {"U2", "Reorder"}, {"U2", "Settings"}};
        System.out.println(helper(input).toString());

        Map<List<String>, Integer> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("zhang");list1.add("brian");
        map.put(list1, 1);
        list2.add("zhang");list2.add("brian");
        map.put(list2, 2);
        System.out.println(map.size()); // 1
    }

    public static List<String> helper(String[][] input) {
        Map<String, List<String>> map = new HashMap<>();

        for (String[] str : input) {
            map.putIfAbsent(str[0], new ArrayList<>());
            map.get(str[0]).add(str[1]);
        }

        Map<List<String>, Integer> mapCount = new HashMap<>();

        int max = 0;
        List<String> res = null;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> pList = entry.getValue();
            for (int i = 0, j = 3; j <= pList.size(); i++, j++) {
                int count = mapCount.getOrDefault(entry.getValue().subList(i, j), 0) + 1;
                if (count > max) {
                    max = count;
                    res = entry.getValue().subList(i, j);
                }
                mapCount.put(entry.getValue().subList(i, j), mapCount.getOrDefault(entry.getValue().subList(i, j), 0) + 1);
            }
        }

        System.out.println("mapCount:" + mapCount);
        return res;
    }
}