package microsoft.ms2020;

/**
 * Created by brianzhang on 10/12/20.
 */
public class LargestAlphabeticCharacter {
    public static void main(String[] args) {
        System.out.println(solution("admeDCAB"));
    }


    public static String solution(String str) {

        boolean[] upper = new boolean[26];
        boolean[] lower = new boolean[26];

        for(char c : str.toCharArray()){
            if(Character.isLowerCase(c)) lower[c-'a'] = true;
            if(Character.isUpperCase(c)) upper[c-'A'] = true;
        }

        for(int i=25; i>=0; i--){
            if(upper[i] && lower[i]){
                return String.valueOf((char)(i+'A')); // Character.toString()
            }
        }

        return "NO";
    }
}
