package remitly;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * Created by brianzhang on 6/15/20.
 */
public class GroupAnagram {

    public static void main(String[] args) {

    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String ss = new String(cs);
            List<String> list = map.getOrDefault(ss, new ArrayList<>());
            list.add(str);
            map.put(ss, list);
        }

        List<List<String>> res = new ArrayList<>();
        res.addAll(map.values());

        return res;
    }
}
