package turo;

import java.util.*;

public class Dianmian {
    public static void main(String[] args) {
        List<List<Integer>> l1 = new ArrayList<>();
        l1.add(new ArrayList<>(Arrays.asList(2,4,5)));
        l1.add(new ArrayList<>(Arrays.asList(45, 67)));

        System.out.println(getPath(l1));
    }

    public static List<List<Integer>> getPath(List<List<Integer>> inputs) {
        List<List<Integer>> res = new ArrayList<>();
        if (inputs == null || inputs.size() == 0) return res;
        int n = inputs.size();

        List<Integer> list = new ArrayList<>();
        for (int start : inputs.get(0)) {
            dfs(inputs, res, n, 0, list, start);
        }
        return res;
    }

    private static void dfs(List<List<Integer>> inputs, List<List<Integer>> res,
                     int n, int curLevel, List<Integer> list, int cur) {
        // base case
        if (n == curLevel) {
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(cur);
        // main dfs
        for (int next : inputs.get(curLevel)) {
            dfs(inputs, res, n, curLevel + 1, list, next);
        }
        list.remove(list.size() - 1); // backtracking
    }
}
