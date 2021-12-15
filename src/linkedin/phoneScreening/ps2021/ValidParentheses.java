package linkedin.phoneScreening.ps2021;

/**
 * don't need to create additional space
 *
 * Created by brianzhang on 11/28/21.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        int i = -1, j = 0;
        char[] a = s.toCharArray();

        while (j < s.length()) {
            if (i == -1 || !match(a, i, j)) {
                a[++i] = a[j++];
            } else {
                i--;
                j++;
            }
        }

        return i == -1;
    }

    static boolean match(char[] a, int i, int j) {
        if ((a[i] == '(' && a[j] == ')')
                || (a[i] == '{' && a[j] == '}')
                || (a[i] == '[' && a[j] == ']')) {
            return true;
        }
        return false;
    }
}
