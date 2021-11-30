package compass.onsite;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-653964-1-1.html
 *
 * https://www.geeksforgeeks.org/print-all-prime-factors-of-a-given-number/
 *
 * Created by brianzhang on 8/30/20.
 */
public class FindAllPrimeFactors {

    public static void main(String[] args) {
        System.out.println("number of prime factors: " + primeFactors(315));
        System.out.println(primeFactorsDP(315, Arrays.asList(2, 3, 5, 7)));
    }

    // My DP solution - give a prime list
    public static int primeFactorsDP(int n, List<Integer> primeList) {
        int[] dp = new int[n + 1];
        for (int i : primeList) dp[i] = 1;
        // dp[2] = dp[3] = dp[5] = dp[7] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int primeFactor : primeList) {
                if (i % primeFactor == 0) {
                    dp[i] = dp[primeFactor] + dp[i / primeFactor];
                    break;
                }
            }
/*          if (i % 2 == 0) {
                dp[i] = dp[2] + dp[i / 2];
            } else if (i % 3 == 0) {
                dp[i] = dp[3] + dp[i / 3];
            } else if (i % 5 == 0) {
                dp[i] = dp[5] + dp[i / 5];
            }*/
        }

        return dp[n];
    }

    // A function to print all prime factors of a given number n
    public static int primeFactors(int n) {
        int count = 0;
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            System.out.println(2 + " ");
            count++;
            n /= 2;
        }

        // n must be odd at this point. So we can skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                count++;
                System.out.println(i + " ");
                n /= i;
            }
        }

        // This condition is to handle the case when n is a prime number greater than 2
        if (n > 2) {
            count++;
            System.out.println(n);
        }
        return count;
    }
}
