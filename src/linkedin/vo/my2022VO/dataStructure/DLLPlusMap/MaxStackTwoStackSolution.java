package linkedin.vo.my2022VO.dataStructure.DLLPlusMap;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/max-stack/description
 *
 * f: 5
 * Created by brianzhang on 11/12/20.
 */
public class MaxStackTwoStackSolution {
    public static void main(String[] args) {
        MaxStackTwoStackSolution stack = new MaxStackTwoStackSolution();
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

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxNumTrack = new Stack<>(); // use to record current max number in each steps
    public MaxStackTwoStackSolution() {}
    //O(1);
    public void push(int x) {
        stack.push(x);
        if (maxNumTrack.isEmpty())
            maxNumTrack.push(x);
        else
            maxNumTrack.push(Math.max(x, maxNumTrack.peek()));
    }
    //O(1);
    public int pop() {
        maxNumTrack.pop();
        return stack.pop();
    }
    //O(1);
    public int top() {
        return stack.peek();
    }
    //O(1);
    public int peekMax() {
        return maxNumTrack.peek();
    }
    //O(n);
    public int popMax() {
        int res = maxNumTrack.peek();
        Stack<Integer> tmp = new Stack<>(); // temp stack
        while (top() != res) {
            tmp.push(pop());
        }
        pop(); // remove the maximum number
        while (!tmp.isEmpty()) {
            push(tmp.pop()); // push the back the number on top of maximum number in original stack
        }
        return res;
    }
}
