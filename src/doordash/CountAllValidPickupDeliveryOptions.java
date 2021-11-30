package doordash;

import java.util.*;

/**
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/
 *
 * Created by brianzhang on 7/21/20.
 */
public class CountAllValidPickupDeliveryOptions {

    public static void main(String[] args) {
        System.out.println(getAllOptions(2));

        System.out.println(isValidPD("P1,D1,P2,D2"));
        System.out.println(isValidPD("D1,P1"));
        System.out.println(isValidPD("P1,P2,P3,D3,D2,D1"));
        System.out.println(isValidPD("P1,P2,D2"));
        System.out.println(isValidPD("P2,D1"));
    }

    /**
     * Assume we have already n - 1 pairs, now we need to insert the nth pair.
     * To insert the first element, there are n * 2 - 1 choices of position。
     * To insert the second element, there are n * 2 choices of position。
     * So there are (n * 2 - 1) * n * 2 permutations.
     * Considering that delivery(i) is always after of pickup(i), we need to divide 2. So it's (n * 2 - 1) * n.
     */
    public static int getAllOptions(int n) {
        int mod = 1_000_000_007;
        long res = 1;

        for (int i = 1; i <= n; i++) {
            res = res * (i * 2 - 1) * i % mod;
        }

        return (int) res;
    }

    /**
     * P1,D1,P2,D2 ==> valid,
     # D1,P1 ==> invalid
     # P1,P2,P3,D3,D2,D1 ==> valid,
     # P1,P2,D2 ===> invalid
     # P2,D1 ==> invalid
     */
    // 给一串String 判断是不是Valid. P一定要在D前面 并且 有P1就得有D1， 有P2就得有D2， 只有D2没P2就不行
    public static boolean isValidPD(String pdStr){

        List<String> ps = new ArrayList();

        String[] pds = pdStr.split(",");

        for(String str : pds){
            if(str.startsWith("D")){
                if(ps.isEmpty() || !ps.contains(str.substring(1))){
                    return false;
                }

                ps.remove(str.substring(1));
            }

            if(str.startsWith("P")){
               ps.add(str.substring(1));
            }
        }

        return ps.isEmpty();
    }
}
