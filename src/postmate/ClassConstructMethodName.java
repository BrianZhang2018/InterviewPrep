package postmate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 6/20/20.
 */
public class ClassConstructMethodName {

    public static void main(String[] args) {
        System.out.println(helper("abbzccc", "babzzcz"));
    }

    public static boolean helper(String className, String methodName){
        Map<Character, Integer> cMap = new HashMap<>();
        Map<Character, Integer> mMap = new HashMap<>();

        for(Character c : className.toCharArray()){
            cMap.put(c, cMap.getOrDefault(c, 0) + 1);
        }

        for(Character c : methodName.toCharArray()){
            mMap.put(c, mMap.getOrDefault(c, 0) + 1);
        }

        if(!mMap.keySet().equals(cMap.keySet())) return false;

        int cKeyFreq = mMap.values().stream().reduce(0, (a, b) -> a+b);
        int mKeyFreq = cMap.values().stream().reduce(0, (a, b) -> a+b);

        if(cKeyFreq != mKeyFreq) return false;

        return true;
    }
}
