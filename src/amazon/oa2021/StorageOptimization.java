package amazon.oa2021;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-716744-1-1.html
 *
 * Created by brianzhang on 2/13/21.
 */
public class StorageOptimization {

    public static void main(String[] args) {
        System.out.println(solution(3, 2, new int[]{1}, new int[]{1}));
    }

    public static long solution(int m, int n, int[] h, int[] v){
        return (long) getMaxCut(m, h) * getMaxCut(n, v);
    }

    public static long getMaxCut(int length, int[] reserve){

        final Set<Integer> set = new HashSet<>(length);
        for (int i = 1; i < length; ++i) {
            set.add(i);
        }
        for (int i : reserve) {
            set.remove(i);
        }
        final int[] cuts = set.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        if (cuts.length < 1) {
            return length;
        }
        // the initial cut
        int max = cuts[0];
        for (int i = 1; i < cuts.length; ++i) {
            max = Math.max(max, cuts[i] - cuts[i - 1]);
        }
        // compare the very last cut
        return Math.max(max, length - cuts[cuts.length - 1]);
    }
}
