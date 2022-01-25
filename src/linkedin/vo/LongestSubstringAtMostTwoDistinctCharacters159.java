package linkedin.vo;

/**
 * https://www.lintcode.com/problem/longest-substring-with-at-most-two-distinct-characters/description
 *
 * https://www.1point3acres.com/bbs/thread-690704-1-1.html
 * Created by brianzhang on 11/24/20.
 */
public class LongestSubstringAtMostTwoDistinctCharacters159 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abc"));
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] freq = new int[128];
        int left=0, right=0;
        int k = 2;

        while(right<s.length()){
            char c = s.charAt(right++);
            freq[c]++;

            if(freq[c] == 1) k--;

            if(k<0 && --freq[s.charAt(left++)] == 0) k++;
        }

        return right - left;
    }
}
