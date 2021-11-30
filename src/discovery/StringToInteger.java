package discovery;

/**
 * Created by brianzhang on 12/16/20.
 */
public class StringToInteger {

    public static void main(String[] args) {
        System.out.println(solution("1234"));
    }

    public static String solution(String str){
        long count = 0;
        for(int i= 0; i<str.length(); i++){
            long digit =str.charAt(i) - '0';
            count = count*10 + digit;
        }
        count+=1;

        return String.valueOf(count);
    }
}
