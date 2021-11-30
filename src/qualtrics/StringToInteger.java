package qualtrics;

/**
 * Created by brianzhang on 1/14/21.
 */
public class StringToInteger {
    public int myAtoi(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int index = 0, m= s.length();
        while(index < m && s.charAt(index) == ' '){
            index++;
        }
        int sign = 1;
        if(index<m && (s.charAt(index) == '-' || s.charAt(index) == '+')){
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        int total = 0;
        while(index < m ){
            int digit = s.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            if(Integer.MAX_VALUE/10 < total || (Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE%10 < digit ))
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            total = total *10 + digit;
            index++;
        }

        return total * sign;
    }
}
