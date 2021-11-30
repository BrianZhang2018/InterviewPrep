package compass.onsite;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by brianzhang on 10/16/19.
 */
public class StringCompressAndDecode {

    public static void main(String[] args) {
        StringCompressAndDecode test = new StringCompressAndDecode();
        System.out.println(test.decodeString("a1b2c2x"));
        System.out.println(test.compress("aaabbc"));
      /*  System.out.println(test.compress(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(test.decodeStringBFS("3[a]2[bc]"));
        System.out.println(test.decodeStringBFS("10[bc]")); // this is why we do " k= k*10 +ch -'0' "*/
    }

    // decode string Aa1b2c2x -> Aabbccx
    public String decodeString(String str) {

        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (k >= 1) { // e.g. "a1" -> "a"
                    Character c = sb.charAt(sb.length() - 1);
                    for (int i = 0; i < k - 1; i++) {  // k-1: if k=1, don't need add again
                        sb.append(c);
                    }
                    k = 0;
                }
                sb.append(ch);
            } else {
                k = k * 10 + ch - '0';
            }
        }
        return sb.toString();
    }

    // compression: aaabb -> a3b2, abb -> ab2
    public String compress(String s) {

        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0, count = 0;
        while (index < charArr.length) {
            char c = charArr[index];
            while (index < charArr.length && c == charArr[index]) {
                index++;
                count++;
            }
            sb.append(c);
            if (count != 1) {
                sb.append((char) (count + '0'));
            }
            count = 0;
        }
        return sb.toString();
    }

    // "3[a]2[bc]"
    public String decodeString1(String s) {
        Deque<Character> queue = new ArrayDeque();
        for(char c : s.toCharArray()) queue.add(c);
        return dfs(queue);
    }

    public String dfs(Deque<Character> dq){
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while(!dq.isEmpty()){
            char c = dq.poll();
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if (c == '[') {
                String sub = dfs(dq);
                for(int j=0; j<num; j++){
                    sb.append(sub);
                }
                num = 0;
            }else if(c == ']'){
                break;
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }

}