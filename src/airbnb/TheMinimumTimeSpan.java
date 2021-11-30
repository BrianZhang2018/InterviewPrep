package airbnb;

import java.util.*;
/**
 * 第一轮: 给一个总时间比如说10个小时，几个可以booking的时间段的时间 1.5, 2, 3, etc，每个时间段可以book不止一次，要求把所有时间都book满，求最少的时间段数目
 *
 * 像是DP问题，其实就是用greedy思路直接做就行
 * Created by brianzhang on 2/20/21.
 */
public class TheMinimumTimeSpan {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(11.5, new Double[]{1.5, 2.0, 2.5}).toArray()));
        System.out.println(Arrays.toString(solutionI(11.5, new Double[]{1.5, 2.0, 2.5}).toArray()));
        System.out.println(new TheMinimumTimeSpan().solutionBacktrack(11.5, new Double[]{1.5, 2.0, 2.5}));
    }

    // greedy - for loop version
    public static List<Double> solution(double totalTime, Double[] periods){

        List<Double> res = new ArrayList<>();
        Arrays.sort(periods, Collections.reverseOrder());

        for(int i=0; i<periods.length;i++){
            if(totalTime - periods[i] < 0) continue;

            totalTime -= periods[i];
            res.add(periods[i]);
            while((totalTime - periods[i]) >= periods[i]){
                totalTime-=periods[i];
                res.add(periods[i]);
            }

            if(totalTime == 0) break;

        }
        return res;
    }

    // same with above solutionBFS, just the while loop version
    public static List<Double> solutionI(double totalTime, Double[] periods){
        Arrays.sort(periods, Collections.reverseOrder());

        int index = 0;
        List<Double> pl = new ArrayList<>();
        while(index < periods.length) {

            if(totalTime - periods[index] < 0){
                index++;
                continue;
            }

            pl.add(periods[index]);
            totalTime -= periods[index];
            if(totalTime == 0) return pl;

            if(totalTime < periods[index]){
                index++;
            }
        }

        return pl;
    }

    // 最初的想法，但是想多了，其实完全不用往回看
    public int solutionBacktrack(double totalTime, Double[] periods){
        Arrays.sort(periods, Collections.reverseOrder());
        List<Double> res = backtrack(periods, 0, totalTime, new ArrayList<>());
        System.out.println(Arrays.toString(res.toArray()));
        return res.size();
    }

    public List<Double> backtrack(Double[] periods, int index, Double leftTime, List<Double> pl){

        if(leftTime == 0) return pl;

        if(index >= periods.length) return null;

        for(int i=index; i<periods.length; i++){
            if(leftTime - periods[i] < 0) continue;

            int nextIndex = i;
            leftTime -= periods[i];
            pl.add(periods[i]);
            if(leftTime < periods[i]){
                nextIndex = i+1;
            }
            List<Double> res = backtrack(periods, nextIndex, leftTime, pl);
            if(res != null) return res;

            pl.remove(pl.size()-1);
            leftTime += periods[i];
        }

        return null;
    }

}
