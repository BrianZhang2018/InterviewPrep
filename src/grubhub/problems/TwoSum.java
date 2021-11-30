package grubhub.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brianzhang on 10/16/19.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{2,7,11,15, 3, 6};

       for(int[] i : twoSum(arr, 9)){
           System.out.println(i[0] + "  " + i[1]);
       }
    }

    public static List<int[]> twoSum(int[] nums, int target) {
        List<int[]> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        int index = 0;
        while(index < nums.length){
            if(map.containsKey(target - nums[index])){
                res.add(new int[]{index, map.get(target-nums[index])});
            }else{
                map.put(nums[index], index);
            }
            index++;
        }
        return res;
    }
}
