package grubhub.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by brianzhang on 10/16/19.
 */
public class ValidParentheses {
    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){
            if(c == '('){
                stack.add(')');
            }else if(c ==  '{'){
                stack.add('}');
            }else if(c == '['){
                stack.add(']');
            }else if(stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        return stack.isEmpty(); // the s only contains "{"
    }

    //map + stack
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(char c : s.toCharArray()) {
            if(map.containsKey(c)) {
                stack.push(c);
            } else {
                if(!stack.isEmpty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
