package affirm;

import java.util.Stack;

/**
 * Created by brianzhang on 10/16/19.
 */
public class StringCompressionOrDecoding {

    public static void main(String[] args) {
        StringCompressionOrDecoding test = new StringCompressionOrDecoding();
        System.out.println(test.decodeString("a1b2c2x"));
        System.out.println(test.compress("aaabbc"));

        System.out.println(test.decodeString1("3[a]2[bc]"));
        System.out.println(test.decodeString1("10[bc]")); // this is why we do " k= k*10 +ch -'0' "

        //System.out.println(test.decodeStringHasNumberCharacter("/44/53b5/72a4c"));
    }

    // decode string: Aa1b2c2x -> Aabbccx
    public String decodeString(String str) {

        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (k >= 1) {
                    Character c = sb.charAt(sb.length() - 1);
                    for (int i = 0; i < k - 1; i++) {  // k-1: if k=1, don't need append again
                        sb.append(c);
                    }
                    k = 0;
                }
                sb.append(ch);
            } else if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            }
        }
        return sb.toString();
    }

    // str= "/44b5/72a4c"
    public String decodeStringHasNumberCharacter(String str) {

        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (int i=0 ; i<str.length(); i++) {
            char cc = str.charAt(i);
            if (Character.isLetter(cc)) {
                if (k >= 1) {
                    Character c = sb.charAt(sb.length() - 1);
                    for (int j = 0; j < k - 1; j++) {  // k-1: if k=1, don't need append again
                        sb.append(c);
                    }
                    k = 0;
                }
                sb.append(str.charAt(i));
            } else if (Character.isDigit(cc)) {
                k = k * 10 + cc - '0';
            } else if(cc == '/'){ // handle the number character

                if(k>0) {
                    for(int j=0; j<k-1; j++){
                        sb.append(sb.charAt(sb.length()-1));
                    }
                    k=0;
                }

                sb.append(str.charAt(++i));
                int repeat = str.charAt(++i) - '0';
                for(int j=0; j<repeat-1; j++){
                    sb.append(sb.charAt(sb.length()-1));
                }
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
                sb.append(count);
            }
            count = 0;
        }
        return sb.toString();
    }


    public String decodeString1(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                numStack.push(k);
                strStack.push(curr);
                curr = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder prevStr = strStack.pop();
                for (k = numStack.pop(); k > 0; --k){
                    prevStr.append(curr);
                }
                curr = prevStr;
            } else{
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}