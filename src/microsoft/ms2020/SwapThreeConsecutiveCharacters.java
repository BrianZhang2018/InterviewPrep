package microsoft.ms2020;

/**
 * Created by brianzhang on 10/12/20.
 */
public class SwapThreeConsecutiveCharacters {

    public static void main(String[] args) {
        System.out.println(solution("baabab"));
        System.out.println(solution("baaaaab"));
    }

    static int solution(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        int minSwap = 0;

        char prev= s.charAt(0);

        int count=1;

        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==prev){
                count++;
            }else{
                if(count>=3){
                    minSwap += count/3;
                }
                count=1;
                prev = s.charAt(i);
            }
        }

        if(count>=3){
            minSwap += count/3;
        }

        return minSwap;
    }
}
