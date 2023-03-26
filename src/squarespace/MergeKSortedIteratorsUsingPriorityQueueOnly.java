package squarespace;

import category.model.Pair;
import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/345744
 *
 * Created by brianzhang on 3/22/21.
 */
public class MergeKSortedIteratorsUsingPriorityQueueOnly {

    static class MergingIterator implements Iterator<Integer> {

        private final PriorityQueue<Pair<Iterator<Integer>, Integer>> priorityQueue;


        public MergingIterator(List<Iterator<Integer>> iterators) {

            priorityQueue = new PriorityQueue<>(Comparator.comparing(Pair::getValue));
            init(iterators);
        }

        /**
         * O(size * log(size)) where size is Length of iterators list
         */
        private final void init(List<Iterator<Integer>> iterators) {
            for (Iterator<Integer> iterator : iterators) {

                if (iterator.hasNext()) {

                    Integer value = iterator.next();
                    priorityQueue.offer(new Pair<>(iterator, value));
                }
            }
        }

        /**
         * O(1) where size is Length of iterators list
         */
        public boolean hasNext() {
            return !priorityQueue.isEmpty();
        }

        /**
         * O(log(size))
         */
        public Integer next() {

            Pair<Iterator<Integer>, Integer> poll = priorityQueue.poll();

            Integer toReturn = poll.getValue();
            if (poll.getKey().hasNext()) {
                Integer next = poll.getKey().next();
                priorityQueue.offer(new Pair<>(poll.getKey(), next));
            }

            return toReturn;

        }
    }
}
