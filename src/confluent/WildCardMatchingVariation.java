package confluent;

/**
 * 题就是wild card match， 第一题 0/1 个*， follow up 多个*
 *
 */
public class WildCardMatchingVariation {
    public static void main(String[] args) {
        System.out.println(isMatch("acdcb", "acdcb"));
    }

    // greedy +  backtracking
    static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, lastStarMatchIdx = 0, lastStarIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length() && str.charAt(s) == pattern.charAt(p)){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                lastStarIdx = p;
                lastStarMatchIdx = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (lastStarIdx != -1){
                p = lastStarIdx + 1;
                lastStarMatchIdx++;
                s = lastStarMatchIdx;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}
