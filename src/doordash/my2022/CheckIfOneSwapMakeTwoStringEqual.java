package doordash.my2022;

import java.util.*;

public class CheckIfOneSwapMakeTwoStringEqual {
    public boolean areAlmostEqual(String s1, String s2) {
        List<Integer> l = new ArrayList();
        for(int i=0; i<s1.length(); i++) {
            if(l.size() > 2) return false;

            if(s1.charAt(i) != s2.charAt(i)) {
                l.add(i);
            }
        }

        return l.size() == 0 ||
                (l.size() == 2 &&
                        s1.charAt(l.get(0)) == s2.charAt(l.get(1))
                        && s1.charAt(l.get(1)) == s2.charAt(l.get(0)));
    }
}
