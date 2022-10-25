package companies.stash;

import java.util.*;
import java.util.PriorityQueue;
/**
 * 10/14/2019
 */
public class FindMeanAndMedianOfScores {   
    public static void main(String[] args) {
        //Get the Median Score
        PriorityQueue<Float> pq = new PriorityQueue<>();
        pq.offer(1f);
        pq.add(2f);
        pq.add(1f);
        pq.add(4f);//pq.add(5f);

        //turn pq to array
        Float[] fa = new Float[pq.size()];
        Float[] arr = pq.toArray(fa); // nice
 
        //Get Median score: if the array size is even: take the average of two middle number.
        float res = 0;
        boolean isEven = pq.size() % 2 == 0 ? true : false;
        int median = pq.size() / 2;
        if (isEven) {
            res = (arr[median] + arr[median - 1]) / 2;
        } else {
            res = arr[median];
        }
        System.out.println(res);

        //Arrays.asList exercise
        List<String> list = Arrays.asList(new String[] { "aa", "bb" });
        List<String> list1 = Arrays.asList("aa", "bb");
        for (String str : list1) {
            System.out.println(str);
        }

        System.out.println(Arrays.toString(arr));
        //System.out.println(1 / 0); arithmetic exception here as devide by 0 
        
        String[][] deepArray = new String[][] { { "John", "Mary" }, { "Alice", "Bob" } };
        System.out.println(Arrays.deepToString(deepArray));

        String gfg1 = String.join(" < ", new String[] { "aa", "bb" }); 
  
        System.out.println(gfg1); 
       
    }

}