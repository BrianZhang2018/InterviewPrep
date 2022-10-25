package crowdStrike;

public class BalanceBracket {
    public static void main(String[] args) {
        System.out.println(balance("<>>>", 2));
        System.out.println(balance("<><<", 2));
        System.out.println(balance("<>><", 1));
        System.out.println(balance("<>>><>", 1));
    }

    public static boolean balance(String str, int maxReplacement) {
        int leftCount = 0;
        for(char c : str.toCharArray()) {
            if(maxReplacement == 0) return false;

            if(c == '<') leftCount++;
            else leftCount--;

            if(leftCount < 0) {
                maxReplacement--;
                leftCount++;
            }
        }

        return leftCount == 0 ? true : leftCount <= maxReplacement;
    }
}
