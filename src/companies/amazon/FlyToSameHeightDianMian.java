package companies.amazon;

import java.util.HashMap;
import java.util.Stack;

/**
 * 亚麻电面
 * <p>
 * BQ 大概15分钟：
 * 描述一个技术上复杂的情景和你怎么解决的.
 * 描述一个和同事们意见不同的情景.
 * <p>
 * Coding:
 * 给一个array，每一个元素代表了一个树的高度，你只能从一个树飞到另一个相同高度的树，求总共有多少路线（每个路线只包括两个树）。
 * 比如 [6, 4, 3, 4, 6] 一共4种路线（注意, 6 -> 6 和 6 <- 6 是两种不同的情况）
 * 再比如 [3, 8, 3] 有0种可能路线, 因为8太高了 ， 3越不过去。
 * <p>
 * 用单调栈解决这个问题
 * <p>
 * Created by brianzhang on 2/19/19.
 */
public class FlyToSameHeightDianMian {
    public static void main(String[] args) {
        System.out.println(findRoutes(new int[]{3, 5, 5, 4, 5, 3}));
    }

    public static int findRoutes(int[] array) {
        Stack<Integer> stack = new Stack();
        HashMap<Integer, Integer> map = new HashMap<>();
        int route = 0;
        for (int i = 0; i < array.length; i++) {
            if (stack.size() == 0 || stack.peek() >= array[i]) {
                stack.add(array[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() <= array[i]) {
                    int val = stack.pop();
                    if (map.containsKey(val) || val == array[i]) {
                        route += 2;
                    } else {
                        map.put(val, 1);
                    }
                }
                map.clear();
                stack.add(array[i]);
            }
        }
        return route;
    }

}
