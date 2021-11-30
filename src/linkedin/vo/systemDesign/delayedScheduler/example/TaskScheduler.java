package linkedin.vo.systemDesign.delayedScheduler.example;

import java.util.concurrent.DelayQueue;

/**
 * Created by brianzhang on 11/18/20.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        DelayQueue<Task> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}
