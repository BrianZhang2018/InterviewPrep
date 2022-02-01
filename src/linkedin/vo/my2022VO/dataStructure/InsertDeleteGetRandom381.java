package linkedin.vo.my2022VO.dataStructure;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 *
 * duplicates-allowed
 * Created by brianzhang on 11/24/20.
 */
public class InsertDeleteGetRandom381 {
    public static void main(String[] args) {
        InsertDeleteGetRandom381 test = new InsertDeleteGetRandom381();
        System.out.println(test.insert(1));
        System.out.println(test.insert(1));
        System.out.println(test.insert(1));
    }
    Map<Integer, LinkedHashSet<Integer>> map;
    List<Integer> list;
    Random rand = new Random();
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom381() {
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
        list = new ArrayList();
    }
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isContain = map.containsKey(val);
        if(!isContain) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !isContain;
    }
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        int idx = map.get(val).iterator().next(); // return the first element from linkedHashSet
        map.get(val).remove(idx); // Using LinkedHashSet can be considered as O(1) if remove the first element
        if(idx < list.size()-1){ // skip the below swap operation if it's the last element
            int lastElement = list.get(list.size()-1);
            list.set(idx, lastElement);
            map.get(lastElement).remove(list.size()-1);
            map.get(lastElement).add(idx);
        }
        list.remove(list.size()-1);
        if(map.get(val).isEmpty()) map.remove(val);
        return true;
    }
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
