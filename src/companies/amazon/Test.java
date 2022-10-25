package companies.amazon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 7/15/18.
 */
public class Test {

    public static void main(String[] args){
        HashMap map = new HashMap();

        Map syncmap = Collections.synchronizedMap(map);
    }
}
