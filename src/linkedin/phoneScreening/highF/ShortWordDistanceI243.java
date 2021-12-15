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
        int index_1 = -1, index_2 = -1, min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index_1 = i;
                if(index_2!=-1) min = Math.min(min, index_1 - index_2);
            }
            if (words[i].equals(word2)) {
                index_2 = i;
                if(index_1!=-1) min = Math.min(min, index_2 - index_1);
            }
        }
        return min;
    }
}
