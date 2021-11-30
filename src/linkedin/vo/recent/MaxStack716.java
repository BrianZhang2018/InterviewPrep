package linkedin.vo.recent;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/max-stack/description
 *
 * f: 5
 * Created by brianzhang on 11/12/20.
 */
public class MaxStack716 {
    public static void main(String[] args) {
        MaxStack716 stack = new MaxStack716();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        stack.push(2);
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.popMax());//  -> 5
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.peekMax()); // -> 5
        System.out.println(stack.popMax()); // -> 5
        System.out.println(stack.peekMax()); // -> 2
    }

    Stack<Integer> data = new Stack<>();
    Stack<Integer> maxTrack = new Stack<>();
    public MaxStack716() {}
    //O(1);
    public void push(int x) {
        data.push(x);
        if (maxTrack.isEmpty()) {
            maxTrack.push(x);
        }
        else {
            maxTrack.push(Math.max(x, maxTrack.peek()));
        }
    }
    //O(1);
    public int pop() {
        maxTrack.pop();
        return data.pop();
    }
    //O(1);
    public int top() {
        return data.peek();
    }
    //O(1);
    public int peekMax() {
        return maxTrack.peek();
    }
    //O(n);
    public int popMax() {
        int res = maxTrack.peek();
        Stack<Integer> tmp = new Stack<>();
        while (top() != res) {
            tmp.push(pop());
        }
        pop();
        while (!tmp.isEmpty()) {
            push(tmp.pop());
        }
        return res;
    }
}
