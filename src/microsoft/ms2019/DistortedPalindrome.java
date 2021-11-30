package microsoft.ms2019;

/**
 * Created by brianzhang on 10/20/19.
 */
public class DistortedPalindrome {

    public static void main(String[] args) throws java.lang.Exception {
        String input = "mamad";
        DistortedPalin(input);
        System.out.println(input);
    }

    private static void DistortedPalin(String name) {
        if (!isPalindromicform(name)) {
            System.out.println("Impossible");
            return;
        }
        int count = 0;
        int i = 0;
        int j = name.length() - 1;
        char[] ch = name.toCharArray();
        while (j > i) {
            if (ch[i] != ch[j]) {
                int k = j;
                for (k = j; k > i; k--) {
                    if (ch[i] == ch[k]) break;
                }
                for (; k < j; k++) {
                    ch[k] = ch[k + 1];
                    count++;
                }
                ch[j] = ch[i];
            }
            i++;
            j--;
        }
        System.out.println(count);
    }

    private static boolean isPalindromicform(String name) {
        int[] in = new int[26];
        for (int i = 0; i < name.length(); i++) {
            in[name.charAt(i) - 'a']++;
        }
        if (name.length() % 2 == 0) {
            for (int i = 0; i < in.length; i++) {
                if (in[i] % 2 != 0) return false;
            }
            return true;
        }
        int count = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] % 2 != 0) count++;
        }
        if (count < 2) return true;
        return false;
    }


}
