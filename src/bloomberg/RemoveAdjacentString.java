package bloomberg;

import javafx.util.Pair;
import java.util.*;

/**
 * Frequency: 2
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii
 *
 * Created by brianzhang on 5/3/20.
 */
public class RemoveAdjacentString {

    public static void main(String[] args) {
        RemoveAdjacentString rs = new RemoveAdjacentString();
        System.out.println(rs.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(rs.removeDuplicates1("deeedbbcccbdaa", 3));
    }

    public static String removeDuplicates(String s, int k) {
        Deque<Pair<Character, Integer>> stack = new ArrayDeque();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek().getKey() == c){
                int count = stack.pop().getValue() + 1;
                if (count < k){
                    stack.push(new Pair(c, count));
                }
            }else{
                stack.push(new Pair(c, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            Pair<Character, Integer> p = stack.pop();
            int repeat = p.getValue();
            char c = p.getKey();

            while(repeat-- > 0){
                sb.append(c);
            }
        }

        return sb.reverse().toString();
    }


    // timeout
    public String removeDuplicates1(String s, int k) {
        int count = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) // be careful: i+1
                count++;
            else count = 1;

            if (count == k)
                s = removeDuplicates1(s.substring(0, i - k + 2) + s.substring(i + 2), k);
        }
        return s;
    }


    public String removeDuplicatesRecursive(String s, int k) {
        if(s.length() < k) return s;

        StringBuilder sb = new StringBuilder(s);
        helper(sb, k);
        return sb.toString();
    }

    public void helper(StringBuilder sb, int k){
        int count = 1;
        for(int i=1; i<sb.length(); i++){
            if(sb.charAt(i-1) == sb.charAt(i)){
                count++;
                if(count == k){
                    sb.delete(i-k+1, i+1);
                    helper(sb, k);
                    break;
                }
            }else{
                count = 1;
            }
        }
    }
}