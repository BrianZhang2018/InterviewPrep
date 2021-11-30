package apple;

import java.util.*;

/**
 * Created by brianzhang on 2/1/21.
 */
public class ReorganizeString {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aaabcd"));
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> count = new HashMap<>();

        for (char c : S.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));
        maxHeap.addAll(count.keySet());

        Queue<Character> wait = new LinkedList<>();
        int k = 2;
        StringBuilder sb = new StringBuilder();

        while(!maxHeap.isEmpty()) {
            char current = maxHeap.poll();
            sb.append(current);
            count.put(current, count.get(current) - 1);
            wait.add(current);

            if (wait.size() < k) {
                continue;
            }

            char front = wait.poll();
            if (count.get(front) > 0) {
                maxHeap.offer(front);
            }
        }

        return sb.length() == S.length() ? sb.toString() :"";
    }
}
