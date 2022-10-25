package companies.RallyHealthy;

import java.util.*;

/**
 * Reserve 4 continuous seats for 4 members family
 * Created by brianzhang on 3/22/20.
 */
public class FlightReservationFor4MembersFamily {

    public static void main(String[] args) {
        System.out.println(solution(2, "1A 2F 1G"));
    }

    public static int solution(int N, String S) { // "S" is all reserved seats
        // write your code in Java SE 8
        if(S == null || S.length() == 0)
            return 2*N;

        String[] str = S.split(" ");
        List<String> oc = new ArrayList<>(Arrays.asList(str));
        int res = 0;
        for(int i=1; i<=N; i++){
            if(!oc.contains(i+"B") && !oc.contains(i+"C") && !oc.contains(i+"D") && !oc.contains(i+"E") && !oc.contains(i+"F") && !oc.contains(i+"G") && !oc.contains(i+"H") &&!oc.contains(i+"J")){
                res += 2;
            }else if(!oc.contains(i+"B") && !oc.contains(i+"C") && !oc.contains(i+"D") && !oc.contains(i+"E")){
                res += 1;
            }else if(!oc.contains(i+"F") && !oc.contains(i+"G") && !oc.contains(i+"H") &&!oc.contains(i+"J")){
                res += 1;
            }else if(!oc.contains(i+"D") && !oc.contains(i+"E") && !oc.contains(i+"F") && !oc.contains(i+"G")){
                res += 1;
            }
        }

        return res;
    }
}
