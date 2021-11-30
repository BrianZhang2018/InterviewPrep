package amazon.onsite;

import java.util.TreeMap;

/**
 * Created by brianzhang on 3/17/21.
 */
public class SerializeDeserializeTree {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(9, 9);
        tm.put(12, 12);

        System.out.println(tm.ceilingKey(9));
    }
}
