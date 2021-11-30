package microsoft.ms2020.vo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by brianzhang on 12/26/20.
 */
public class DesignBoundedBlockingQueue1188 {

}

// solution -1
class BoundedBlockingQueue {
    Semaphore full;
    Semaphore empty;
    Semaphore mutex;
    Deque<Integer> q;
    public BoundedBlockingQueue(int capacity) {
        q = new LinkedList<>();
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void enqueue(int element) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        q.addFirst(element);
        mutex.release();
        full.release();
    }

    public int dequeue() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        int ret = q.pollLast();
        mutex.release();
        empty.release();
        return ret;
    }

    public int size() {
        return q.size();
    }
}

// solution-2

class BoundedBlockingQueueLock {
    Deque<Integer> deq;
    int size;
    Object lock;
    public BoundedBlockingQueueLock(int capacity) {
        deq = new LinkedList<>();
        size = capacity;
        lock = new Object();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock) {
            while(deq.size() == size) {
                lock.wait();
            }
            deq.addLast(element);
            lock.notify();
        }
    }

    public int dequeue() throws InterruptedException {
        int val = 0;
        synchronized(lock) {
            while(deq.isEmpty()) {
                lock.wait();
            }
            val = deq.removeFirst();
            lock.notify();
        }
        return val;
    }

    public int size() {
        return deq.size();
    }
}