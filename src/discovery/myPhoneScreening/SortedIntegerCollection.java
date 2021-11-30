package discovery.myPhoneScreening;

import java.util.*;

/**
 * Create a class to store the integer which is sorted
 *
 * Created by brianzhang on 12/21/20.
 */
public class SortedIntegerCollection {
    public static void main(String[] args) {
      List<Integer> l = new ArrayList<>();
      l.add(1);
      l.add(5);
      l.add(10);

      l.remove(Integer.valueOf(1));
      System.out.println(l.get(0));

    }
    List<Integer> collection;

    public SortedIntegerCollection(){collection = new ArrayList<>();}

    public int getByIndex(int index){
        return collection.get(index);
    }

    public void add(int val){
        collection.add(val);
    }

    public void remove(int val){ // O(n), can we reduce the time complexity?
        if(collection.contains(val)){
            for(int i=0; i<collection.size(); i++){
                if(collection.get(i) == val) {
                    collection.remove(i);
                    return;
                }
            }
        }
    }
}


class SortedIntegerCollection2 {
    /*LinkedHashSet<Integer> collection;

    public SortedIntegerCollection2(){collection = new LinkedHashSet<>();}

    public int getByIndex(int index){
        return collection.get(index);
    }

    public void add(int val){
        collection.add(val);
    }

    public void remove(int val){ // O(n), can we reduce the time complexity?
        collection.remove
    }*/
}