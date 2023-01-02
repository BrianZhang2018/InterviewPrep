package confluent;

import java.util.Formatter;
import java.util.concurrent.*;

public class DelayQueueExample {
    public static void main(String[] args) {
        BlockingQueue<DelayedElement<Integer>> queue = new DelayQueue<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long startTime = System.currentTimeMillis();
        // Enqueue random numbers with random initial delays
        for (int i = 0; i < 7; i++) {
            int randomNumber = random.nextInt(10, 100);
            int initialDelayMillis = random.nextInt(100, 5000);
            DelayedElement<Integer> element =
                    new DelayedElement<>(randomNumber, initialDelayMillis);
            queue.offer(element);
            System.out.printf(
                    "[%3dms] queue.offer(%s)   --> queue = %s%n",
                    System.currentTimeMillis() - startTime, element, queue);
        }
        // Dequeue all elements
        while (!queue.isEmpty()) {
            try {
                DelayedElement<Integer> element = queue.take();
                System.out.printf(
                        "[%3dms] queue.poll() = %s --> queue = %s%n",
                        System.currentTimeMillis() - startTime, element, queue);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class DelayedElement<E extends Comparable<E>> implements Delayed {
    private final E e;
    private final long initialDelayMillis;
    private final long expiration;
    public DelayedElement(E e, long initialDelayMillis) {
        this.e = e;
        this.initialDelayMillis = initialDelayMillis;
        this.expiration = System.currentTimeMillis() + initialDelayMillis;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long remainingDelayMillis = expiration - System.currentTimeMillis();
        return unit.convert(remainingDelayMillis, TimeUnit.MILLISECONDS);
    }
    @Override
    public int compareTo(Delayed o) {
        DelayedElement<?> other = (DelayedElement<?>) o;
        return Long.compare(expiration, other.expiration);
    }
    @Override
    public String toString() {
        return new Formatter().format("{%s, %dms}", e, initialDelayMillis).toString();
    }
}
