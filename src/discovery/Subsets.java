package discovery;

import java.util.*;

/**
 * https://leetcode.com/problems/subsets/solution/
 * Subsets: 2^N , since each element could be absent or present.
 *
 * So, if N numbers, the T: O(n*2^n)
 * S: O(n)
 * Created by brianzhang on 7/25/18.
 */
public class Subsets {
    public static void main(String[] args) {
        String[] nums = new String[]{"i", "love", "ice", "cream"};
        System.out.println(subsets(nums, "icecream"));
    }

    public static List<List<String>> subsets(String[] dict, String word) {
        List<String> validWords = new ArrayList<>();
        for(String s : dict){
            if(word.contains(s)) validWords.add(s);
        }
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), validWords, 0);
        return list;
    }

    private static void backtrack(List<List<String>> list, List<String> tempList, List<String> dict, int start) {
        list.add(new ArrayList<>(tempList));

        for (int i = start; i < dict.size(); i++) {
            tempList.add(dict.get(i));
            //the 'i+1' is used to go next number
            backtrack(list, tempList, dict, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
