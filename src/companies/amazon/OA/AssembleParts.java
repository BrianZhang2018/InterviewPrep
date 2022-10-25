package companies.amazon.OA;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AssembleParts{
    public static void main(String[] args){  
        List<Integer> list = new ArrayList<Integer>();
        list.add(8);
        list.add(4);
        list.add(6);
        list.add(12);
        Collections.sort(list);
        System.out.println(minimumTime1(4, list));
    }

    static int minimumTime(int numOfParts, List<Integer> parts){
        
       PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
       for(int part : parts){
           pq.add(part);
       }

       int result = 0;
       while(pq.size() > 1){
            int next = pq.poll() + pq.poll();
            result += next;
            pq.add(result);
       }
        return result;
    }

   static int minimumTime1(int numOfParts, List<Integer> parts){
        
        int prevTime = parts.get(0) + parts.get(1);
        int result = prevTime;

        for(int i= 2; i< numOfParts; i++){
            prevTime += parts.get(i);
            result += prevTime;
        }

        return result;
    }
}

