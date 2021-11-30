package apple;

import java.util.Iterator;

/**
 * Apple leetcode
 *
 * Design a lookAhead iterator, the input is a basic iterator which only has 2 methods: hasNext() and next().
 * In your lookAhead iterator, you should have another method called lookAhead(), which read the next value but not move to next
 *
 * Created by brianzhang on 2/1/21.
 */
public class PeekingIterator implements Iterator<Integer>{

    private Iterator<Integer> iterator;

    private Integer next = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if(iterator.hasNext()){
            next = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iterator.hasNext()?iterator.next():null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null || iterator.hasNext();
    }
}
