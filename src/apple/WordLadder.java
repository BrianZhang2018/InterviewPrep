package apple;

import java.util.TreeMap;

/**
 * 店面 套壳word ladder， bfs做的。followup 要优化时间复杂度， 结果居然是要要求优化到b-direction bfs有点神奇。
 * Created by brianzhang on 1/25/21.
 */
public class WordLadder {
    public static void main(String[] args) {
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(9, "10");
        tm.put(12, "10");
        tm.put(11, "12");
        System.out.println(tm.ceilingKey(10));
    }
}
