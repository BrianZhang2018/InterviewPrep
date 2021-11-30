package coinbase;

import javafx.util.Pair;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://www.1point3acres.com/bbs/thread-649675-1-1.html
 * <p>
 * Created by brianzhang on 7/8/20.
 */
public class TheoryOfCryptographyTextbook {

    public static void main(String[] args) {

        TheoryOfCryptographyTextbook test = new TheoryOfCryptographyTextbook();
        test.maxHeap.add(100.0);
        test.maxHeap.add(99.9);
        test.maxHeap.add(98.9);
        System.out.println(test.sell(101));
        test.minHeap.add(100.0);
        System.out.println(test.offer(101));

        Timestamp curr = new Timestamp(System.currentTimeMillis());
        curr.setTime(curr.getTime() + 1 * 24 * 60 * 60 * 1000);
        System.out.println(curr);

        Timestamp curr1 = new Timestamp(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000);
        System.out.println(curr1);
    }

    PriorityQueue<Double> minHeap = new PriorityQueue<>();
    PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Pair<Double, Timestamp>> minHeap1 = new PriorityQueue<>((a, b) -> {
        if (a.getKey() - b.getKey() > 0) return 1;
        if (a.getKey() - b.getKey() < 0) return -1;
        return 0;
    });
    PriorityQueue<Pair<Double, Timestamp>> maxHeap1 = new PriorityQueue<>((a, b) -> {
        if (a.getKey() - b.getKey() > 0) return -1;
        if (a.getKey() - b.getKey() < 0) return 1;
        return 0;
    });

    public double offer(double offerPrice) {
        if (minHeap.isEmpty() || minHeap.peek() > offerPrice) {
            maxHeap.add(offerPrice);
            return 0.0;
        }

        return minHeap.poll();
    }

    public double sell(double sellPrice) {
        if (maxHeap.isEmpty() || maxHeap.peek() < sellPrice) {
            minHeap.add(sellPrice);
            return 0.0;
        }

        return maxHeap.poll();
    }

    private int lifeSpan = 5 * 24 * 60 * 60 * 1000;

    public double offer1(double offerPrice) {
        Timestamp curr = new Timestamp(System.currentTimeMillis());
        while (!minHeap1.isEmpty() && minHeap1.peek().getValue().getTime() + lifeSpan < curr.getTime()) {
            minHeap1.poll();
        }

        if (minHeap1.isEmpty() || minHeap1.peek().getKey() > offerPrice) {
            maxHeap1.add(new Pair<>(offerPrice, new Timestamp(System.currentTimeMillis())));
            return 0.0;
        }

        return minHeap1.poll().getKey();
    }

    public double sell1(double sellPrice) {
        Timestamp curr = new Timestamp(System.currentTimeMillis());
        while (!maxHeap1.isEmpty() && maxHeap1.peek().getValue().getTime() + lifeSpan < curr.getTime()) {
            maxHeap1.poll();
        }

        if (maxHeap1.isEmpty() || maxHeap1.peek().getKey() < sellPrice) {
            minHeap1.add(new Pair<>(sellPrice, new Timestamp(System.currentTimeMillis())));
            return 0.0;
        }

        return maxHeap1.poll().getKey();
    }
}

