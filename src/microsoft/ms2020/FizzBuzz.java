package microsoft.ms2020;

import java.util.*;

/**
 * Created by brianzhang on 10/12/20.
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();

        for (int num = 1; num <= n; num++) {

            boolean divisibleBy2 = (num % 2 == 0);
            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);

            String numAnsStr = "";

            if (divisibleBy2) {
                // Divides by 2, add XXXX
                numAnsStr += "XXXX";
            }

            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }

            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }

            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }
}
