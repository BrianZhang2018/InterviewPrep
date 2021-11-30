package linkedin.vo.systemDesign.delayedScheduler.example;

import java.util.concurrent.DelayQueue;

/**
 * Created by brianzhang on 11/18/20.
 */
public class TaskConsumer implements Runnable {
    private DelayQueue<Task> q;

    public TaskConsumer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = q.take();
                System.out.println("Take " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}