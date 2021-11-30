package compass.onsite;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/
 *
 * Allow duplicate elements
 * Created by brianzhang on 8/25/20.
 */
public class InsertDeleteRandomAllowDuplicate {

    public static void main(String[] args) {
        InsertDeleteRandomAllowDuplicate test = new InsertDeleteRandomAllowDuplicate();
    }

    Map<Integer, Set<Integer>> map = new HashMap();
    List<Integer> list =  new ArrayList();
    Random rand = new Random();

    public InsertDeleteRandomAllowDuplicate() {}

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (!contain) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !contain;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        // Using LinkedHashSet can be considered as O(1) if we only get the first element to remove.
        int idx = map.get(val).iterator().next(); // return the first element from LinkedHashSet
        map.get(val).remove(idx);
        if (idx < list.size() - 1) {
            int lastIndex = list.size()-1;
            int lastNum = list.get(lastIndex);
            list.set(idx, lastNum);

            map.get(lastNum).remove(lastIndex);
            map.get(lastNum).add(idx);
        }

        list.remove(list.size() - 1);
        if (map.get(val).isEmpty()) map.remove(val);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

}
