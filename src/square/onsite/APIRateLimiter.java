package square.onsite;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/system-design/124558/uber-rate-limiter
 *
 * Whenever you expose a web service / api endpoint, you need to implement a rate limiter to prevent abuse of the service (DOS attacks).
 *
 * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, deny a request if that client has made more than 100 requests in the past second.
 *
 * 解题思路： 类似于LRU，LinkedHashMap
 * Created by brianzhang on 4/1/21.
 */
public class APIRateLimiter {

    public static void main(String[] args) {
        APIRateLimiter limiter = new APIRateLimiter();
        System.out.println("test1 " + limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("Sleeping for 1 second");
        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("test1 " + limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test1 " +limiter.isAllow("test1"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test2 " +limiter.isAllow("test2"));
        System.out.println("test1 " +limiter.isAllow("test1"));
    }

    class HitCounter {
        Queue<Long> q = null;
        public HitCounter() {
            q = new LinkedList<>();
        }

        public boolean hit(long timestamp) {
            while(!q.isEmpty() && timestamp - q.peek() >= TIME_LIMIT) q.poll();

            if(q.size() < REQUEST_LIMIT)
            {
                q.offer(timestamp);
                return true;
            }

            return false;
        }
    }

    private final int REQUEST_LIMIT = 3;
    private final long TIME_LIMIT = 1000L; // 1s = 1000 millisecond

    private final Map<String, HitCounter> clientHitMap = new HashMap<>();

    public boolean isAllow(String clientId) {
        HitCounter h = clientHitMap.get(clientId);
        long curTime = System.currentTimeMillis();

        if(h == null) {
            h = new HitCounter();
            clientHitMap.put(clientId, h);
        }

        return h.hit(curTime);
    }
}
