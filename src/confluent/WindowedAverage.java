package confluent;

import java.util.*;
import java.util.concurrent.*;

/**
 * 1. Windowed Average. you are given a set of key, value pair. Each key, value expires after k millisec.
 * I can ask you to get me a specific key. Also, I can ask you to return me the average.
 * The catch was to make sure to pruce the DLL before each call.
 * For ex: if <"foo", 100> is saved at t = 1 and time expiry is 3ms then after 3 ms get("foo") should return key not found.
 *
 * LRU
 */
public class WindowedAverage {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.size();
        ConcurrentHashMap<Integer, Integer> cm = new ConcurrentHashMap<>();

        System.out.println( Runtime.getRuntime().availableProcessors());

        Executors.newCachedThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++) {
                    System.out.println(Thread.currentThread().getId());
                    System.out.println("hahah");
                }
            }
        });

    }
}
