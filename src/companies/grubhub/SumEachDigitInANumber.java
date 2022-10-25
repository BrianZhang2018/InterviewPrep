package companies.grubhub;

public class SumEachDigitInANumber {

    public static void main(String[] args) {
        System.out.println(getSumIteratively(123));
        System.out.println(getSumRecursively(12));
    }

    public static int getSumIteratively(int number) {
        if (number < 10)
            return number;

        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number = number / 10;
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