package compass.onsite;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * Created by brianzhang on 8/25/20.
 */
public class InsertDeleteRandom {
    Map<Integer, Integer> map;
    List<Integer> list;

    /** Initialize your data structure here. */
    public InsertDeleteRandom() {
        map = new HashMap();
        list = new ArrayList();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        else{
            map.put(val, list.size()); // firstly do map put, then list add
            list.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        else{
            int loc = map.get(val);
            if(loc < list.size()-1){
                // replace with the last number for "loc" value
                int lastNum = list.get(list.size()-1);
                list.set(loc, lastNum);
                map.put(lastNum, loc);
            }
            map.remove(val);
            list.remove(list.size()-1);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }
}
