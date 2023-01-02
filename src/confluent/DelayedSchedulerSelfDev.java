package confluent;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
/**
 * https://silhding.github.io/2021/03/13/How-to-design-a-delayed-scheduler-in-Java/
 */
public class DelayedSchedulerSelfDev {
    public static void main(String[] args) {
        DelayedSchedulerSelfDev delayedScheduler= new DelayedSchedulerSelfDev();
        // ThreadLocalRandom random = ThreadLocalRandom.current();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 7; i++) {
            int delayTime = 5000;
            DelayedTask element =
                    new DelayedTask(() -> System.out.println(System.currentTimeMillis()), delayTime);
            delayedScheduler.put(element);
            System.out.printf("[%3dms] queue.offer(%s)   --> queue = %s%n",
                    System.currentTimeMillis() - startTime, element, delayedScheduler);
        }
        // Dequeue all elements
        while (!delayedScheduler.queue.isEmpty()) {
            try {
                DelayedTask element = delayedScheduler.take();
                System.out.printf(
                        "[%3dms] queue.poll() = %s --> queue = %s%n",
                        System.currentTimeMillis() - startTime, element, delayedScheduler);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public final PriorityBlockingQueue<DelayedTask> queue;
    public final ReentrantLock lock;
    public final Condition available;
    public DelayedSchedulerSelfDev() {
        this.queue = new PriorityBlockingQueue();
        this.lock = new ReentrantLock();
        this.available = this.lock.newCondition();
    }

    public boolean put(DelayedTask task) {
        this.lock.lock();
        try {
            this.queue.offer(task);
            if (task == this.queue.peek()) {
                this.available.signal();   // wake up one thread (not all!)
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public DelayedTask take() throws InterruptedException {
        this.lock.lock();
        try {
            while (true) {
                DelayedTask peekTask = this.queue.peek();
                if (peekTask == null) {
                    // no elemnets; wait!
                    available.await();
                } else {
                    long delay = peekTask.getDelay(TimeUnit.NANOSECONDS);
                    if (delay <= 0) return this.queue.poll();
                    available.awaitNanos(delay);
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

class DelayedTask extends FutureTask implements Delayed {
    private final long startTime;
    private final Runnable task;

    public DelayedTask(Runnable task, long delayTime) {
        super(task, null);  // null is the return value for the runnable tasks
        this.task = task;
        this.startTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = this.startTime - System.currentTimeMillis();
        return unit.convert(diff, unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS),
                o.getDelay(TimeUnit.MILLISECONDS));
    }
}