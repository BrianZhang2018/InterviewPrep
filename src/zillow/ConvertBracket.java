package zillow;

import java.util.*;

public class ConvertBracket {
    public static void main(String[] args) {
        System.out.println(convert("((a)"));
        System.out.println(convert(")(a)))"));
        System.out.println(convert(")("));
    }

    public static String convert(String str) {
        Stack<Integer> stack = new Stack<>();
        String[] res = str.split("");
        for (int i = 0; i < res.length; i++) {
            String s = res[i];
            if (s.equals("(")) {
                stack.push(i);
                res[i] = "1";
            } else if (s.equals(")")) {
                if (!stack.isEmpty()) {
                    res[stack.pop()] = "0";
                    res[i] = "0";
                } else {
                    res[i] = "-1";
                }
            }
        }

        return String.join("", res);
    }
}
