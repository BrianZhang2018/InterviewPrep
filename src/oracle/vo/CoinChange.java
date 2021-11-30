package oracle.vo;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-689198-1-1.html
 *
 * coding部分 要求写一个思路有点类似李口coin change的题？ e.g. 10000s convert to hous + minutes + senconds的形式（保证给定的时间不会超过一天）
 * 然后要求用hashmap     followup问了 如果时间会超过一天你要怎么改你现在的code（让它更generic）   再followup 如果现在变成 给一堆pennies 要convert成 quarter + dime + nickel + pennies你要怎么改   再再followup 如果现在你的柜台有一些种类的coin有数量限制 你要怎么改
 *
 * Created by brianzhang on 12/24/20.
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(convertSecondToHMS(10000).toArray()));
    }

    public static List<Integer> convertSecondToHMS(int s) {
        int hour = 60 * 60, minute = 60;
        int time = s;
        int resH=0, resM=0, resS;

        if(time/hour > 0){
            resH = time/hour;
        }

        time = s - resH*hour;

        if(time/minute > 0){
            resM = time/minute;
        }

        resS = time - minute*resM;

        return Arrays.asList(resH, resM, resS);
    }
}
