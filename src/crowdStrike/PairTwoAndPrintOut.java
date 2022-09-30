package crowdStrike;

import java.util.*;

public class PairTwoAndPrintOut {
    public static void main(String[] args) {
        int[] input = {1,1,1,2,1,2,3,4};
        Set<Integer> set = new HashSet<>();
        for(int i : input) {
            if(!set.add(i)) { // haha
                System.out.println(i);
                set.remove(i);
            }
        }
    }
}
