package oracle.phoneScreening.mine;

import java.util.*;

/**
 *
 * [1,3,6,7]
 *
 * [2,4,8,9]
 * ^
 * [1,2,4,6]
 *        ^
 * 我面的题 phone screen
 * Created by brianzhang on 11/5/20.
 */
public class ThreeArrayFindTarget {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,6,7}, new int[]{2,4,8,9}, new int[]{1,2,4,6},  5));
    }

    public static boolean solution(int[] arr1, int[] arr2, int[] arr3, int target){

        for(int i=0; i<arr1.length; i++){

            int m = 0, n = arr3.length-1;

            while(m<arr2.length && n>=0) {
                if(arr1[i] + arr2[m] + arr3[n] == target){
                    return true;
                } else if(arr1[i] + arr2[m] + arr3[n] > target){
                    n--;
                } else {
                    m++;
                }
            }
        }

        return false;
    }

    public static boolean solution2(int[] arr1, int[] arr2, int[] arr3, int target){

        Set<Integer> set = new HashSet();
        for(int i : arr1) set.add(i);

        for(int i=0; i<arr1.length; i++){
            for(int j=0; j< arr1.length; j++){

                if(set.contains(target - arr1[i] - arr2[j])) return true;
            }
        }

        return false;
    }
}
