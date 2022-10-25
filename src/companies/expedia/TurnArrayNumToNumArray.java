package companies.expedia;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by brianzhang on 3/3/19.
 */
public class TurnArrayNumToNumArray {

    public static void main(String[] args) {
        String input = "40 40 40 40 29 29 29 29 29 29 29 29 57 57 92 92 92 92 92 86 86 86 86 86 86 86 86 86 86";
        String[] inputs = input.split(" ");
        String res = solutionWithHashMap(inputs);
        System.out.println(res);
    }

    public static String solutionWithHashMap(String[] inputs) {

        int[] nums = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            nums[i] = Integer.valueOf(inputs[i]);
        }

        LinkedHashMap<Integer, Integer> hmap = new LinkedHashMap<>();
        for (int i : nums) {
            hmap.put(i, hmap.getOrDefault(i, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
            sb.append(entry.getValue());
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
