package box;

/**
 * https://leetcode.com/problems/number-of-1-bits/discuss/55099/Simple-Java-Solution-Bit-Shifting
 *
 * Created by brianzhang on 9/20/20.
 */
public class NumberOfBits {
    public static void main(String[] args) {
        Integer i = 0b00000000000000000000000000000101;
        Integer j = 0b11111111111111111111111111111011;
        System.out.println(i);
        System.out.println(j);
        System.out.println(hammingWeight(j));
    }

    public static int hammingWeight(int n) {
        int ones = 0;
        while(n!=0) {
            ones = ones + (n & 1);
            n = n>>>1;
        }
        return ones;
    }
}
