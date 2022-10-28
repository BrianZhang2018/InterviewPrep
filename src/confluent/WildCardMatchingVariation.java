package confluent;

/**
 * 题就是wild card match， 第一题 0/1 个*， follow up 多个*
 */
public class WildCardMatchingVariation {
    public static void main(String[] args) {
        System.out.println(isMatch1("abab", "a*b"));
        System.out.println(isMatch2("aba", "*************"));
    }
    // 0/1个*
    public static boolean isMatch1(String str, String pattern) {
        int s = 0, p = 0;
        while (s < str.length() && p < pattern.length()) {
            if (str.charAt(s) == pattern.charAt(p)) {
                s++;
                p++;
            } else if (pattern.charAt(p) == '*') {
                int rightLen = pattern.length() - p - 1;
                if(rightLen == 0) return true;

                String patternStarRight = pattern.substring(pattern.length()-rightLen);
                String strRight = str.substring(str.length() - rightLen);
                return patternStarRight.equals(strRight);
            } else {
                return false;
            }
        }

        return s == str.length() && p == pattern.length();
    }

    // follow up 多个*: greedy: two pointer + backtracking
    static boolean isMatch2(String str, String pattern) {
        int s = 0, p = 0, lastStrMatchIdx = 0, lastStarIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length() && str.charAt(s) == pattern.charAt(p)){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                lastStarIdx = p;
                lastStrMatchIdx = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (lastStarIdx != -1){
                p = lastStarIdx + 1;
                lastStrMatchIdx++;
                s = lastStrMatchIdx;
            }
            //current pattern pointer is not star, last patter pointer was not *.
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}
