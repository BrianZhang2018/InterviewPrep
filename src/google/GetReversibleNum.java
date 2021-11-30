package google;

import java.util.*;

/**
 * 马拉松比赛总共有650个人参加，一共有650个号码（1-650），每个人拿号码的时候有可能拿反了，
 * 6变成9，91变成16，写一个程序，s列出所有可能出错的原号码
 *
 * https://www.1point3acres.com/bbs/thread-476874-1-1.html
 *
 * Created by brianzhang on 8/16/20.
 */
public class GetReversibleNum {

    public static void main(String[] args) {
        solution(100).forEach(a -> System.out.println(a));
    }

    public static List<Integer> solution(int n) {

        Map<Integer, Integer> reversibleNumMap = new HashMap<>();
        reversibleNumMap.put(1, 1);
        reversibleNumMap.put(6, 9);
        reversibleNumMap.put(9, 6);
        reversibleNumMap.put(0, 0);
        reversibleNumMap.put(8, 8);

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int k = i;
            int reverseNum = 0;
            boolean skip = false;

            while (k > 0) {
                Integer r = reversibleNumMap.get(k % 10);
                if (r != null) {
                    reverseNum = reverseNum * 10 + r;
                    k = k / 10;
                } else {
                    skip = true;
                    break;
                }
            }

            if (!skip) {
                if (reverseNum != i) {
                    res.add(i);
                }
            }

        }

        return res;

    }
}
