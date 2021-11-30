package compass.onsite;

import java.util.*;

/**
 * Created by brianzhang on 9/1/20.
 */
public class FindDistanceOneLetter {

    public static void main(String[] args) {
        helper(Arrays.asList("apple", "app", "aeppl", "appj"), "appl").forEach(a -> System.out.println(a));
    }

    public static List<String> helper(List<String> dict, String target) {

        List<String> res = new ArrayList<>();
        if (dict.contains(target)) {
            res.add(target);
        }

        for (String str : dict) {
            if (str.length() >= target.length() + 2 || str.length() <= target.length() - 2) continue;

            for (int i = 0; i < target.length(); i++) {
                if (str.length() > i && str.charAt(i) != target.charAt(i)) {
                    if (isMatch(target, str.substring(0, i) + str.substring(i + 1)) ||
                            isMatch(target, str.substring(0, i) + String.valueOf(target.charAt(i)) + str.substring(i)) ||
                            isMatch(target, str.substring(0, i) + String.valueOf(target.charAt(i)) + str.substring(i + 1))) {
                        res.add(str);
                    }
                    break;
                } else if (i >= str.length() && target.length() - str.length() == 1) {
                    if (isMatch(target, new StringBuffer(str).append(target.charAt(i)).toString())) {
                        res.add(str);
                    }
                    break;
                }
            }

            if (!res.contains(str) && str.length() - target.length() == 1) {
                if (isMatch(target, new StringBuilder(str).deleteCharAt(str.length() - 1).toString()))
                    res.add(str);
            }
        }

        return res;
    }

    public static boolean isMatch(String target, String input) {
        return target.equals(input);
    }
}
