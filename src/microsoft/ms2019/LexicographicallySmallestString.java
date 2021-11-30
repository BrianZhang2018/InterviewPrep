package microsoft.ms2019;

/**
 * 这种题很简单，就是从左到右check，比较每一个字符，对字典排序的大的字符进行处理
 * Lexicographically compare the string is if the letter is bigger in the front, this string is bigger.
 * "abc" < "acz" as 'b' < 'c'
 *
 * Created by brianzhang on 10/20/19.
 */
public class LexicographicallySmallestString {

    public static void main(String[] args) {
        System.out.println(solution("abcdz"));
        System.out.println(solution("zabcdz"));
        System.out.println(solution("abcczccdz"));
    }

    public static String solution(String input) {
        StringBuilder sb = new StringBuilder();
        char[] inputArray = input.toCharArray();
        boolean foundOne = false;

        for (int i = 0; i < inputArray.length - 1; i++) {
            if (!foundOne && inputArray[i] > inputArray[i + 1]) {
                foundOne = true;
                continue;
            }
            sb.append(inputArray[i]);
        }

        if (foundOne) {
            sb.append(inputArray[inputArray.length - 1]);
        }

        return sb.toString();
    }
}
