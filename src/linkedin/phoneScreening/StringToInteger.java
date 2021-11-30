package linkedin.phoneScreening;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Created by brianzhang on 11/11/20.
 */
public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi(" -42"));
        System.out.println(myAtoi("x43"));
    }

    public static int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        int index=0, total=0, sign=1;

        while(index<str.length() && str.charAt(index) == ' ') index++;

        if(index<str.length() && (str.charAt(index) == '-' || str.charAt(index) == '+')){
            sign = str.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        if(index == str.length()) return 0;

        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit>9 || digit<0) break;

            if(Integer.MAX_VALUE/10 < total || (Integer.MAX_VALUE/10 ==total && Integer.MAX_VALUE%10 < digit))
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            total = total*10 + digit;
            index++;
        }

        return total * sign;
    }
}
