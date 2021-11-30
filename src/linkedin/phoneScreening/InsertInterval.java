package linkedin.phoneScreening;

import java.util.TreeSet;

/**
 * Created by brianzhang on 11/11/20.
 */
public class InsertInterval {

    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(5);
        ts.add(9);
        ts.add(1);

        System.out.println(ts.floor(3));
    }
}
