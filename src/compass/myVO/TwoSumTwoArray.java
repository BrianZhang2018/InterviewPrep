package compass.myVO;

import java.util.*;

/*
 a = [7, -10, 9, 9, 23, -34, 26, ...], sa = a.size()
 b = [9, 10, 7, 26, 7, -18, ....], sb = b.size()

 a = [8, 8 8 8 88 8]
 b = [8 8 8 88 8 8 8]

 a[i] + b[j] == k, k = 16

 the first element always come from array "a"

 [(0, 0), (1, 3), (2, 2), (3, 2), (2, 4), (3, 4), ....]   indices <=========

*/
/**
 * Firstly, write a working code, then refactor. Don't need struggle with the some tricky/better writing way in the beginning.
 * Created by brianzhang on 9/4/20.
 */
public class TwoSumTwoArray {

    public static void main(String[] args) {
        int[] b = {7, -10, 9, 9, 23, -34, 26};
        int[] a = {9, 10, 7, 26, 7, -18};

        for (int[] i : helper(a, b, 16))
            System.out.println(Arrays.toString(i));

    }

    /**
     * tricky requirement: the index of "a" has to be in the first position in all the results
     */
    public static List<int[]> helper(int[] a, int[] b, int target) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] tempL = a, temp = b; // default (a.length >= b.length)

        if (a.length < b.length) {
            tempL = b;
            temp = a;
        }

        for (int i = 0; i < temp.length; i++) {
            map.putIfAbsent(temp[i], new ArrayList<>());
            map.get(temp[i]).add(i);
        }

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < tempL.length; i++) {
            if (map.containsKey(target - tempL[i])) {
                List<Integer> indices = map.get(target - tempL[i]);
                if (a.length >= b.length) {
                    for (Integer j : indices) {
                        res.add(new int[]{i, j});
                    }
                } else {
                    for (Integer j : indices) {
                        res.add(new int[]{j, i});
                    }
                }
            }

        }

        return res;
    }

}

