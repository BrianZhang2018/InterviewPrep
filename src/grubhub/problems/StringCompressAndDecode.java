package grubhub.problems;

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

    //decode string; Aa1b2c2x -> Aabbccx
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
            } else if (Character.isDigit(ch)) {
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

   /*
    public String decodeStringBFS(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                numStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder pre = strStack.pop();
                for (k = numStack.pop(); k > 0; --k)
                    pre.append(cur);

                cur = pre;
            } else
                cur.append(ch);
        }
        return cur.toString();
    }*/
}