package postmate.dianmian;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 7/29/20.
 */
public class CarPooling {

    public boolean isCapable(int[][] trips, int capacity) {

        Arrays.sort(trips, (a, b) -> a[1]-b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);

        for(int[] trip : trips){

            while(!pq.isEmpty() && pq.peek()[2] <= trip[1]){
                capacity += pq.poll()[0];
            }

            capacity-= trip[0];
            if(capacity < 0) return false;

            pq.add(trip);
        }

        return true;


    }

}
