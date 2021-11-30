package linkedin.phoneScreening.highF;

/**
 * https://www.lintcode.com/problem/shortest-word-distance/
 *
 * Created by brianzhang on 10/25/20.
 */
public class ShortWordDistanceI243 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"practice", "makes", "perfect", "coding", "makes"},"makes","coding"));
    }

    public static int solution(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
            }
            if (words[i].equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                min = Math.min(min, Math.abs(p1 - p2));
            }
        }
        return min;
    }
}
