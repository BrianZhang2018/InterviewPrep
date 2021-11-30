package apple;

import java.util.*;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 *
 * Created by brianzhang on 1/25/21.
 */
public class RestoreIPAddresses {

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        List<String> curr = new ArrayList<>();
        helper(s, 0, curr);
        return res;
    }

    public void helper(String s, int start, List<String> curr) {
        if(curr.size() == 4) {
            if (start == s.length()) {
                res.add(curr.get(0) + "." +curr.get(1) + "." + curr.get(2) + "." + curr.get(3));
            }
            return;
        }
        for(int i=start; i<s.length(); i++) {
            if(i != start && s.charAt(start) == '0') break; // valid "0.011.255.245", non-valid "000.011.255.245"
            if(Integer.valueOf(s.substring(start,i+1)) > 255) break;

            curr.add(s.substring(start,i+1));
            helper(s, i+1, curr);
            curr.remove(curr.size()-1);
        }
    }
}
