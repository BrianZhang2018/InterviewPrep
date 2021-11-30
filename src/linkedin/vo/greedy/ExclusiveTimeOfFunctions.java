package linkedin.vo.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 *
 * Created by brianzhang on 11/30/20.
 */
public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack();
        int[] res = new int[n];
        int prev = 0;

        for(String log : logs){
            String[] splits = log.split(":");

            if(splits[1].equals("start")){
                if(!stack.isEmpty()) res[stack.peek()] += Integer.valueOf(splits[2]) - prev;

                stack.add(Integer.valueOf(splits[0]));
                prev = Integer.valueOf(splits[2]);  // prev means the start of the interval
            }else{
                res[stack.pop()] += Integer.valueOf(splits[2]) - prev +1;
                prev = Integer.valueOf(splits[2]) + 1;  // prev means the start of next interval, so we need to +1
            }
        }

        return res;
    }
}
