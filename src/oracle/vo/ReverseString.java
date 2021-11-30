package oracle.vo;

import java.math.BigInteger;

/**
 * Created by brianzhang on 12/23/20.
 */
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(reverseStr("apple"));
        System.out.println(reverseStrR("apple", 0, "apple".length()-1));
        getFibonacci(5);
    }

    public static String reverseStr(String s){

        char[] ca = s.toCharArray();

        int i=0, j=s.length()-1;

        while(i <= j){
            char tmp = ca[i];
            ca[i] = ca[j];
            ca[j] = tmp;
            i++; j--;
        }

        return String.valueOf(ca);
    }

    public static String reverseStrR(String s, int i, int j){
        if(i >=j) return s;

        char[] ca = s.toCharArray();
        char tmp = ca[i];
        ca[i] = ca[j];
        ca[j] = tmp;

        return reverseStrR(String.valueOf(ca), i+1, j-1);
    }

    static void getFibonacci(int n){

        for(int i=0; i<n; i++){
            System.out.println(fibonacci(i));
        }
    }

    // Returns Factorial of N
    static BigInteger fibonacci(int n)
    {
        if(n <= 1) return BigInteger.valueOf(n);

        BigInteger i = fibonacci(n-1).add(fibonacci(n-2));
        return i;
    }
}
