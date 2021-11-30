package twitter;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-707893-1-1.html
 *
 * Created by brianzhang on 2/9/21.
 */
public class MapDifference {
    Map<String, String> map1, map2;
    Set<String> keyOnlyInFirstMap, keyOnlyInSecondMap, keyInBothMapWithDifferentValues;

    public MapDifference(Map m1, Map m2){
        keyOnlyInFirstMap = new HashSet<>();
        keyOnlyInSecondMap = new HashSet<>();
        keyInBothMapWithDifferentValues = new HashSet<>();

        map1 = m1;
        map2 = m2;
    }

    public List<Set<String>> getDifferenceBetweenTwoMaps(){

        for(Map.Entry<String, String> entry : map1.entrySet()){
            String key = entry.getKey();
            if(map2.containsKey(key)){
                keyInBothMapWithDifferentValues.add(key);
            }else{
                keyOnlyInFirstMap.add(key);
            }
        }

        for(Map.Entry<String, String> entry : map2.entrySet()){
            String key = entry.getKey();
            if(!map1.containsKey(key)){
                keyOnlyInSecondMap.add(key);
            }
        }

        return Arrays.asList(keyOnlyInFirstMap, keyOnlyInSecondMap, keyInBothMapWithDifferentValues);
    }
}
