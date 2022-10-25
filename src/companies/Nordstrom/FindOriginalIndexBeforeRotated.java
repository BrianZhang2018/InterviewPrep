package companies.Nordstrom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 2/14/20.
 */
public class FindOriginalIndexBeforeRotated {

    public static void main(String[] args) {
        for(int res : findIndex(new int[]{6,8,9,10,1,2,3}, new int[]{3,1,9}))
            System.out.println(res);
    }

    public static List<Integer> findIndex(int[] arr, int[] targets){
        Arrays.sort(arr);
        List<Integer> res = new ArrayList<>();
        for(int target : targets){
            int s=0, e=arr.length-1;
            while(s<=e){
                int mid = s + (e-s)/2;
                if (arr[mid] == target){
                    res.add(mid);
                    break;
                }
                if(arr[mid] < target){
                    s = mid + 1;
                }else{
                    e = mid-1;
                }
            }
        }
        return res;
    }
}
