package hubspot.codingOnsite;

import java.util.*;

public class CanFormPalindrome {

    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        list.remove((Character) 'a');
    }

    static boolean canFormPalindrome(String str) {
        List<Character> list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (list.contains(str.charAt(i)))
                list.remove((Character) str.charAt(i));
            else
                list.add(str.charAt(i));
        }
        // if character length is even list is expected to be empty
        // or if character length is odd list size is expected to be 1
        if (str.length() % 2 == 0 && list.isEmpty() // if string length is even
                || (str.length() % 2 == 1 && list.size() == 1)) // if string length is odd
            return true;
        else
            return false;
    }
}
