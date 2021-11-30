package oracle.vo;

/**
 * https://leetcode.com/problems/remove-k-digits/discuss/88660/A-greedy-method-using-stack-O(n)-time-and-O(n)-space
 *
 * the idea is the smallest possible here is to remove big number from left to right of string
 *
 * Created by brianzhang on 12/18/20.
 */
public class RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
    }

    public static String removeKdigits(String num, int k) {
        char[] buf = new char[num.length()];
        int top = 0, count = k;
        for(int i=0; i<num.length(); i++){
            char c = num.charAt(i);
            while(top > 0 && buf[top-1] > c && count>0){
                top--;
                count--;
            }

            buf[top++] = c;
        }

        int start = 0;
        while(start < num.length() - k && buf[start] == '0') start++;

        return start == num.length() - k ? "0" : new String(buf, start, num.length()-k-start);
    }
}
