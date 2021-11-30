package apple;

import java.util.*;

/**
 * Created by brianzhang on 1/25/21.
 */
public class FindMedianFromDataStream {

    PriorityQueue<Integer> minHeap = new PriorityQueue(); // keep the large value
    PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder()); // keep the little value

    /** initialize your data structure here. */
    public FindMedianFromDataStream() {}

    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(minHeap.size() < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {

        if(minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

        return minHeap.peek();
    }
}
