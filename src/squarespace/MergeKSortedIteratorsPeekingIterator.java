package squarespace;

import java.util.*;

/**
 * Created by brianzhang on 3/22/21.
 */
public class MergeKSortedIteratorsPeekingIterator {

    private static class PeekingIterator<E> implements Iterator<E> {

        private E lastElement = null;
        private Iterator<E> iterator;

        public PeekingIterator(Iterator<E> iterator) {
            this.iterator = iterator;

            if (this.iterator.hasNext())
                lastElement = iterator.next();

        }

        // Returns the next element in the iteration without advancing the iterator.
        public E peek() {


            return lastElement;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public E next() {

            E toReturn = lastElement;
            if (iterator.hasNext())
                lastElement = iterator.next();
            else
                lastElement = null;

            return toReturn;

        }

        @Override
        public boolean hasNext() {
            return lastElement != null;
        }
    }


    /**
     * Using Sorted list like data structure;
     * Using Priority Queue
     */
    static class MergingIterator implements Iterator<Integer> {

        private final PriorityQueue<PeekingIterator<Integer>> priorityQueue;

        public MergingIterator(List<Iterator<Integer>> iterators) {

            priorityQueue = new PriorityQueue<>(Comparator.comparing(PeekingIterator::peek));
            init(iterators);
        }

        /**
         * O(size * log(size)) where size is Length of iterators list
         */
        private final void init(List<Iterator<Integer>> iterators) {
            for (Iterator<Integer> iterator : iterators) {

                if (iterator.hasNext())
                    priorityQueue.offer(new PeekingIterator<>(iterator));
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

            PeekingIterator<Integer> poll = priorityQueue.poll();
            Integer toReturn = poll.next();

            if (poll.hasNext())
                priorityQueue.offer(poll);

            return toReturn;

        }
    }
}
