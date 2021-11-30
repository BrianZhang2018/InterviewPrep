package microsoft.ms2019;

import java.util.*;

/**
 * the trick is to keep a descending order for the frequency of character
 * Created by brianzhang on 10/19/19.
 */
public class OAproblems2 {

    public static void main(String[] args) {
        System.out.println(maxPossibleValue(670));
        System.out.println(getMinDeletionsToMakeFrequencyOfEachLetterUnique("aaaabbbb"));
    }

    //max possible value
    public static int maxPossibleValue(int n){
        char[] ca;
        if(n > 0)
            ca = String.valueOf(n).toCharArray();
        else
            ca = String.valueOf(-n).toCharArray();

        int index = 0;

        if(n >= 0){
            while(index < ca.length && ca[index] >= '5'){
                index++;
            }
        }else{
            while(index < ca.length && ca[index] <= '5'){
                index++;
            }
        }

        String pre = new String(Arrays.copyOfRange(ca, 0, index));
        String after = new String(Arrays.copyOfRange(ca, index, ca.length));

        return Integer.valueOf(pre + "5" + after);
    }

    // aabbffddeaee -> 6
    public static int getMinDeletionsToMakeFrequencyOfEachLetterUnique(String str) {
        // Stores how many times a character occurs.
        // freqOfLetter[0] stores the frequency of letter 'a'.
        Integer[] frequencyOfCharacter = new Integer[26];

        for (int i = 0; i < 26; i++)
            frequencyOfCharacter[i] = 0;

        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            frequencyOfCharacter[s[i] - 'a']++;
        }

        int numOfDeletes = 0;
        Arrays.sort(frequencyOfCharacter, Collections.reverseOrder());

        for (int i = 1; i < frequencyOfCharacter.length; i++) {
            if (frequencyOfCharacter[i] == 0)
                break;
            if (frequencyOfCharacter[i] >= frequencyOfCharacter[i - 1]) {
                if (frequencyOfCharacter[i - 1] == 0) {
                    numOfDeletes += frequencyOfCharacter[i];
                    frequencyOfCharacter[i] = 0;
                } else {
                    numOfDeletes += frequencyOfCharacter[i] - frequencyOfCharacter[i - 1] + 1;
                    frequencyOfCharacter[i] = frequencyOfCharacter[i - 1] - 1;
                }
            }
        }

        return numOfDeletes;
    }

}
