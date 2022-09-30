package square.my2022onsite;

import java.util.*;

/**
 * similar with word break problem
 * tc: 2^n
 */
public class DecodeMorseCode {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(decodeMorseCode(".--").toArray()));
    }

    static String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
            "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", ".." +
            ".", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public static List<String> decodeMorseCode(String s) {
        List<String> res = new ArrayList();
        Map<String, String> morseCodeToLetter = new HashMap<>();
        for (int i = 0; i < alpha.length; i++) {
            morseCodeToLetter.put(morse[i], alpha[i]);
        }
        dfs(s, 0, morseCodeToLetter, new StringBuilder(), res);
        return res;
    }

    public static void dfs(String s, int start, Map<String, String> morseCodeToLetter,
                           StringBuilder sb, List<String> res) {
        if (start == s.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String next = s.substring(start, i + 1); // start, i+1
            if (morseCodeToLetter.containsKey(next)) {
                String letter = morseCodeToLetter.get(next);

                sb.append(letter);
                dfs(s, i + 1, morseCodeToLetter, sb, res);
                sb.delete(sb.length() - letter.length(), sb.length());
            }
        }
    }
}
