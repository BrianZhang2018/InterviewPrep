package tinder.myOA;

import javafx.util.Pair;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Solution: Pair<value, currMinVal> "currMinVal" is the current minimum value when push an new element into the stack
 *
 * Created by brianzhang on 3/5/21.
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.offerFirst(2);

        System.out.println(dq.peek());

        for(int i : dq) System.out.println(i);
    }

    Deque<Pair<Integer, Integer>> stack = new ArrayDeque();

    public MinStack() {}

    public void push(int x) {
        if(stack.size() == 0){
            stack.push(new Pair(x, x));
        }else{
            int minVal = Math.min(stack.peek().getValue(), x);
            stack.push(new Pair(x, minVal));
        }
    }

    public void pop() {
        if(stack.size() == 0) return;

        stack.pop(); // stack.removeFirst()
    }

    public int top() {
        if(stack.size() == 0) return 0;

        return stack.peek().getKey(); // stack.getFirst().getKey()
    }

    public int getMin() {

        return stack.size() == 0 ? 0 : stack.peek().getValue();
    }
}
