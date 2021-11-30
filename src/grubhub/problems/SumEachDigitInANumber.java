package grubhub.problems;

/**
 * Created by brianzhang on 10/16/19.
 */
public class SumEachDigitInANumber {

    public static void main(String[] args) {
        System.out.println(getSumIteratively(123));
        System.out.println(getSumRecursively(12));
    }

    public static int getSumIteratively(int n) {
        if (n < 10)
            return n;

        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    public static int getSumRecursively(int n) {
        if (n < 10)
            return n;

        int sum = n % 10;
        n = n / 10;
        sum += getSumRecursively(n);
        return sum;
    }
}
