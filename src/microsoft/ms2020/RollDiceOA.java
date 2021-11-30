package microsoft.ms2020;

import java.util.*;
/**
 * Created by brianzhang on 10/12/20.
 */
public class RollDiceOA {

    public static void main(String[] args) {
        RollDiceOA rda = new RollDiceOA();
        System.out.println(Arrays.toString(rda.solution(new int[]{1,5,6}, 4, 3)));
    }

    public int[] solution(int[] arr, int f, int m){
        List<Integer> list = new ArrayList<>();
        for(int i : arr) list.add(i);
        return backtracking(list, f, m);
    }

    public int[] backtracking(List<Integer> list, int f, int m){
        if(f == 0) {
           if(sum(list)/list.size() == m) {
               return list.stream().mapToInt(i->i).toArray();
           }
           return null;
        }

        for(int i=1; i<=6; i++){
            list.add(i);
            int[] res = backtracking(list, f-1, m);
            if(res != null) return res;
            list.remove(list.size()-1);
        }

        return null;
    }


    public int sum(List<Integer> list){
        int sum = 0;
        for(Integer i : list) sum+=i;
        return sum;
    }
}
