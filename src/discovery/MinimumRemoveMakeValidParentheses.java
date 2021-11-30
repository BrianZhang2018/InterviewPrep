package discovery;

import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 *
 * Created by brianzhang on 7/5/20.
 */
public class MinimumRemoveMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
    }

    public static int minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);

        Stack<Integer> stack = new Stack<>(); // track the parentheses
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty())
                    sb.setCharAt(i, '*');
                else
                    stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }
        System.out.println(sb.toString());

        int count=0;
        for(char c : sb.toString().toCharArray()){
            if (c == '*') count++;
        }

        return count;
    }
}
