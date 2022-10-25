package companies.amazon;

import java.util.*;

/**
 * Created by brianzhang on 7/12/18.
 */
public class TowSum {

    public List<Map<Integer, Integer>> find2Sum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        List<Map<Integer, Integer>> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            int targetMinus = target - arr[i];

            if (map.get(targetMinus) != null) {
                Map<Integer, Integer> temp = new HashMap<>();
                temp.put(arr[i], targetMinus);
            }
            map.put(arr[i], i);
        }
        return result;
    }

    public List find2Sum2(int[] arr, int target) {

        List<Integer> result = new ArrayList();
        Arrays.sort(arr);

        if (arr != null && arr.length != 0) {
            int start = 0;
            int end = arr.length - 1;

            while (start < end) {

                if (arr[start] + arr[end] > target) {
                    end--;
                }
                if (arr[start] + arr[end] < target) {
                    start++;
                }
                if (arr[start] + arr[end] == target) {
                    result.add(arr[start]);
                    result.add(arr[end]);
                }
            }
        }
        return result;
    }
}
