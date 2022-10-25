package companies.amazon;

import java.util.*;

/**
 * Created by brianzhang on 7/11/18.
 */
public class FindMostFrequentWord {

    public String find(String str, List<String> exclusionList) {

        String[] strs = str.split(" ");

        Map<String, Integer> counterMap = new HashMap<>();

        for(String word : strs){
            // As per the Java documentation, it returns true if and only if this list contains at
            // least one element’s equals method returns true for object which we are searching for.
            if(exclusionList.contains(word)){

            }

        }
        return null;
    }

    public static void main(String[] args){

        Map<String, String> map = new HashMap<>();
        String a = new String("AAA");
        map.put(a, "BBB");


        String b = new String("AAA");

        System.out.println(map.get(b));


        A aO =  new A(1);
        A bO =  new A(1);
        List<A> list = Arrays.asList(aO);
        System.out.println(list.contains(bO));

    }
}

class A{

    int value;

    public A(int value){
        this.value = value;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        return value == a.value;
    }

}
