package companies.Nordstrom;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/382421/JavaPython-3-Iterative-short-codes-w-comment-and-analysis.
 *
 * 这道题的窍门是：When meets the ')', you need reverse the substring within the paired bracket before the ')',
 * then combine it with last substring in stack which will be reversed when you arrive next ')'
 *
 * Created by brianzhang on 2/14/20.
 */
public class ReverseNestedString {

    public static void main(String[] args) {
        String input = "hello(abc(edf(mln)))hij";
        ReverseNestedString rs = new ReverseNestedString();
        System.out.println(rs.reverseParentheses(input));
    }

    public String reverseParentheses(String s) {

        Deque<StringBuilder> dq = new ArrayDeque<>();
        dq.push(new StringBuilder()); // In case the first char is NOT '(', need an empty StringBuilder.
        for (char c : s.toCharArray()) {

            if (c == '(') { // need a new StringBuilder to save substring in brackets pair
                dq.offer(new StringBuilder());
            }else if (c == ')') { // found a matched brackets pair and reverse the substring between them.
                StringBuilder end = dq.pollLast();
                dq.peekLast().append(end.reverse());
            }else { // append the char to the last StringBuilder.
                dq.peekLast().append(c);
            }
        }
        return dq.pollLast().toString();
    }

    // my solution
    public String reverse(String input){

        Stack<Integer> left = new Stack<>();

        char[] inputs = input.toCharArray();
        for(int i=0; i<inputs.length; i++){
            if(inputs[i] == '('){
                inputs[i] = ' ';
                left.add(i+1);
            }else if(inputs[i] == ')'){
                inputs[i] = ' ';
                swap(inputs, left.pop(), i-1);
            }
        }

        String res = new String(inputs);
        res = res.replaceAll("\\s", "");

        return res;
    }

    public void swap(char[] input, int start, int end){

        while(start<end){
            char temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            start++;
            end--;
        }
    }

    public String reverseParentheses1(String s) {
        Stack<String> stack = new Stack<>();
        String str = "";

        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(str);
                str = "";
            }else if(c == ')'){
                StringBuilder sb = new StringBuilder(str);
                str = stack.pop() + sb.reverse().toString();
            }else{
                str += c;
            }
        }

        return str;
    }


}
