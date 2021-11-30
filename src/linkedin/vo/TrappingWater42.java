package linkedin.vo;

import java.util.Stack;

/**
 * monotone stack
 * Created by brianzhang on 11/20/20.
 */
public class TrappingWater42 {

    public static void main(String[] args) {
        TrappingWater42 test = new TrappingWater42();
        System.out.println(test.trap(new int[]{2,1,0,1,2}));
    }

    public int trap(int[] height) {
        int cumulativeWater = 0;
        Stack<Integer> stack = new Stack<>(); //decreasing stack that hold the index of bar
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!stack.isEmpty() && h > height[stack.peek()]) {
                int valley = stack.pop();
                int start = stack.isEmpty()? i-1: stack.peek();
                int curWater =
                        (Math.min(height[start], h) - height[valley]) * (i - start - 1);
                cumulativeWater += curWater;
            }
            stack.push(i);
        }
        return cumulativeWater;
    }
}
