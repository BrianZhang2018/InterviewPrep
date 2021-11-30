package doordash.my2021;

import java.util.*;

/**
 * Created by brianzhang on 11/27/21.
 */
public class ThrottlingGateway {
    public static void main(String[] args) {
        System.out.println(getNumOfDroppedRequest(new int[] {1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,11,11,11,11}));
        System.out.println(getNumOfDroppedRequest1(new int[] {1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,11,11,11,11}).size());
    }

    public static int getNumOfDroppedRequest(int[] requestTime) {
        int ans = 0;
        if(requestTime.length <= 3) return 0;

        for(int i=0; i<requestTime.length; i++) { // "i" is the index of request
            if(i > 2 && requestTime[i] - requestTime[i-3] < 1) { // first throttle condition
                ans++;
                System.out.println("throttle 1 drop: " + requestTime[i]);
            }else if (i > 19 && requestTime[i] - requestTime[i-20] < 10) { // second throttle condition - 10 sec can't exceed 20 transactions
                ans++;
                System.out.println("throttle 2 drop: " +  requestTime[i] );
            }else if (i > 59 && requestTime[i] - requestTime[i-60] < 60) { // third throttle condition
                ans++;
                System.out.println("throttle 3 drop: " +  requestTime[i] );
            }
        }

        return ans;
    }

    public static Set<String> getNumOfDroppedRequest1(int[] requestTime) {
        Set<String> s1 = new HashSet(), s2 = new HashSet(), s3 = new HashSet();
        if(requestTime.length <= 3) return null;

        for(int i=0; i<requestTime.length; i++) { // "i" is the index of request
            if(i > 2 && requestTime[i] - requestTime[i-3] < 1) { // first throttle condition
                s1.add(i + ":" + requestTime[i]);
                System.out.println("first throttle drop: " + i + ":" + requestTime[i]);
            }

            if (i > 19 && requestTime[i] - requestTime[i-20] < 10) { // second throttle condition
                s2.add(i+ ":" + requestTime[i]);
                System.out.println("Second throttle drop: " + i + ":" + requestTime[i]);
            }
            if (i > 59 && requestTime[i] - requestTime[i-59] < 60) { // third throttle condition
                s3.add(i+ ":" + requestTime[i]);
                System.out.println("Third throttle drop: " +  i + ":" + requestTime[i]);
            }
        }

        s1.addAll(s2);
        s1.addAll(s3);
        return s1;
    }
}
