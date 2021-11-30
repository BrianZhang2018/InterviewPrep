package remitly;

import java.util.Stack;

/**
 * Created by brianzhang on 6/15/20.
 */
public class ValidParenthesis {

    public static void main(String[] args) {
    }

    public boolean isValid(String[] strs){
        Stack<String> stack = new Stack<>();
        for(String s : strs){
            if(s.equals("{")){
                stack.push("}"); //store the right bracket
            }else if(s.equals("(")){
                stack.push(")");
            }else if(s.equals("[")){
                stack.push("]");
            }else if(stack.isEmpty() || stack.pop() != s){
                return false;
            }
        }

        return stack.isEmpty();
    }
}
