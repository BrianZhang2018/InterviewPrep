package square;

/**
 * Created by brianzhang on 3/29/21.
 */
public class Test {

    public static void main(String[] args) {

        String[][] schedules =new String[][]{
                {"0",  "17", "-", "40", "60"},
                {"10", "17", "25","-", "50"},
                {"-",  "18", "-", "30", "55"}};

        //System.out.println(earliestArrivalNoTransfer(schedules, 0, 3));
    }

}

class MyPair<T, K> {

    public T key;
    public K value;

    public MyPair(T key, K val){
        this.key = key;
        this.value = val;
    }
}
