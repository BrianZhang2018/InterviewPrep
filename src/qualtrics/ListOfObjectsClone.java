package qualtrics;

import java.util.*;

/**
 * Created by brianzhang on 1/15/21.
 */
public class ListOfObjectsClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Thing input = new Thing("test");
        Thing t1 = new Thing("test1");
        Thing t2 = new Thing("test2");
        Thing t3 = new Thing("test3");
        t1.things = Arrays.asList(t3);
        input.things = Arrays.asList(t1, t2);
        Thing res = cloneObject(input);
        for(Thing t : res.things){
            System.out.println(t.name);
            if(t.things != null)
                System.out.println(Arrays.toString(t.things.toArray()));
        }
    }

    public static Thing cloneObject(Thing input) throws CloneNotSupportedException {
        return new Thing(input.name, input.things);
    }
}

class Thing {
    String name;
    List<Thing> things;

    public Thing(String name){
        this.name = name;
    }

    public Thing(String name, List<Thing> things){
        this.name = name;
        this.things = dfs(things);
    }

    private synchronized List<Thing> dfs(List<Thing> things){
        if (things == null) return null;

        List<Thing> res = Collections.synchronizedList(new ArrayList<>());// thread-safe

        for(Thing t : things){
            Thing temp = new Thing(t.name);
            temp.things = dfs(t.things);
            res.add(temp);
        }

        return res;
    }

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        return new Thing(this.name);
    }

}