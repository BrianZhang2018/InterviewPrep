package linkedin.vo.systemDesign.delayedScheduler.example;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * Created by brianzhang on 11/18/20.
 */

public class Task implements Delayed {
    private String name;
    private long startTime;  // milliseconds

    public Task(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.startTime - ((Task) o).startTime);
    }

    @Override
    public String toString() {
        return "task " + name + " at " + startTime;
    }
}