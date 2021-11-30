package wayfair;

/**
 * Created by brianzhang on 11/9/19.
 */
public class FindTwoCommonSubstring {

    public static void main(String[] args) {
        System.out.println(lcsDy("abcdefgabced", "aabcdgeabcde"));
    }

    public static String lcsDy(String a, String b) {
        int[][] lengths = new int[a.length() + 1][b.length() + 1];

        int maxLen = 0, pos = 0;

        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    lengths[i][j] = lengths[i-1][j-1] + 1;
                    if (lengths[i][j] > maxLen) {
                        maxLen = lengths[i][j];
                        pos = i;
                    }
                } else {
                    lengths[i][j] = 0;
                }
            }
        }

        return a.substring(pos - maxLen, pos + 1).toString();
    }
}
