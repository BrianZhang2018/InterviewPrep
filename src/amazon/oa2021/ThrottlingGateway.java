package amazon.oa2021;

/**
 * Created by brianzhang on 3/2/21.
 */
public class ThrottlingGateway {

    public int solution(int[] requestTime){
        int ans = 0 ;
        for(int i = 0 ;  i < requestTime.length; i++){

            if(i  >2 && requestTime[i] == requestTime[i-3]){
                ans++;
            } else if(i > 19 && (requestTime[i] - requestTime[i-20]) <10){
                ans++;
            } else if( i > 59 && (requestTime[i] - requestTime[i-60]) <60 ){
                ans++;
            }
        }

        return ans;
    }
}
