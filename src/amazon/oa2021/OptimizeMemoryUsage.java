package amazon.oa2021;

import java.util.*;

/**
 * https://aonecode.com/amazon-online-assessment-oa2-optimize-memory-usage
 *
 * Created by brianzhang on 3/8/21.
 */
public class OptimizeMemoryUsage {

    public static void main(String[] args) {

        for(int[] res : optimizeMemoryUsage(new int[]{1,1}, new int[]{1, 1}, 1))
            System.out.println(Arrays.toString(res));

        for(int[] res : getPairs(Arrays.asList(new int[]{1,1}), Arrays.asList(new int[]{1,1}), 1))
            System.out.println(Arrays.toString(res));
    }

    public static List<int[]> optimizeMemoryUsage(int[] foregroundTasks, int[] backgroundTasks, int K) {
        List<int[]> res = new ArrayList();

        if(K == 0 || (foregroundTasks.length == 0 && backgroundTasks.length == 0)){
            res.add(new int[]{-1,-1});
            return res;
        }


        int sum = -1;
        final Set<String> set = new HashSet<>();

        if(foregroundTasks.length == 0){
            for(int i=0; i<backgroundTasks.length; i++){
                if(backgroundTasks[i] != sum){
                    set.clear();
                    sum = backgroundTasks[i];
                }

                set.add(-1 + "," + i);
            }
        }


        for (int i=0; i<foregroundTasks.length;i++) {
            if(foregroundTasks[i] == K && !set.contains(i + "," + -1)){
                if(sum != K) {
                    set.clear();
                    sum = K;
                }
                set.add(i + "," + -1);
            }
            for (int j=0; j<backgroundTasks.length; j++) {

                if(backgroundTasks[j] == K && !set.contains(-1 + "," + j)){
                    if(sum != K) {
                        set.clear();
                        sum = K;
                    }
                    set.add(-1 + "," + j);
                    continue;
                }

                int s = foregroundTasks[i] + backgroundTasks[j];
                if (s > K || s < sum) {
                    continue;
                }
                if (s > sum) {
                    set.clear();
                    sum = s;
                }
                set.add(i+","+j);
            }
        }


        for(String str : set){
            String[] cs = str.split(",");
            res.add(new int[]{Integer.valueOf(cs[0]), Integer.valueOf(cs[1])});
        }

        return res;
    }


    private static List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
        Collections.sort(a, (i,j) -> i[1] - j[1]);
        Collections.sort(b, (i,j) -> i[1] - j[1]);
        List<int[]> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int m = a.size();
        int n = b.size();
        int i =0;
        int j =n-1;
        while(i<m && j >= 0) {
            int sum = a.get(i)[1] + b.get(j)[1];
            if(sum > target) {
                --j;
            } else {
                if(max <= sum) {
                    if(max < sum) {
                        max = sum;
                        result.clear();
                    }
                    result.add(new int[]{a.get(i)[0], b.get(j)[0]});
                    int index = j-1;
                    while(index >=0 && b.get(index)[1] == b.get(index+1)[1]) {
                        result.add(new int[]{a.get(i)[0], b.get(index--)[0]});
                    }
                }
                ++i;
            }
        }
        return result;
    }

}
