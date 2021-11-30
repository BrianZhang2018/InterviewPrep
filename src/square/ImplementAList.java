package square;

import java.util.*;
/**
 * https://www.vogella.com/tutorials/JavaDatastructureList/article.html
 *
 * Created by brianzhang on 3/21/21.
 */
public class ImplementAList<E> {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);

        System.out.println(deque.poll());
    }

    private int currSize = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];

    public ImplementAList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e) {
        if (currSize == elements.length) {
            ensureCapacity();
        }
        elements[currSize++] = e;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    public E get(int i) {
        if (i >= currSize || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i );
        }
        return (E) elements[i];
    }
}
