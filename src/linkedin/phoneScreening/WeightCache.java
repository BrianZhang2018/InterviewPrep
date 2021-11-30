package linkedin.phoneScreening;

import javafx.util.Pair;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 第二题类似LRU Cache但是Eviction条件不同，Put的时候自带权重，要求Evict权重最低的。
 * HashMap+PriorityQueue搞定。然后分析Multi-thread的情况，分析哪里可能是瓶颈，提出可以用ConcurrentHashMap + Lock on Queue。
 *
 * Created by brianzhang on 10/25/20.
 */
public class WeightCache {
    public static void main(String[] args) {
        WeightCache c = new WeightCache();
        c.add(1,1);
        c.add(2,2);
        c.add(3,3);
        c.add(4,4);

/*        while(!c.pq.isEmpty()){
            System.out.println(c.pq.poll().getKey());
        }*/
    }

    int maxCacheSize = 3;
    PriorityBlockingQueue<Pair<Integer, Integer>> pq = new PriorityBlockingQueue<>(10, (a, b) -> a.getValue() - b.getValue());

    public void add(int m, int n){

        synchronized (pq) {  // implicitly reentrant lock - mutual exclusion Lock
            pq.add(new Pair(m, n));
        }

        if(pq.size() > maxCacheSize){
           System.out.println("pop out: " + pq.poll().getKey());
        }
    }
}
