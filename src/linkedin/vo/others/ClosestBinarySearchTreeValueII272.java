package linkedin.vo.others;

import model.TreeNode;
import java.util.*;
/**
 * https://www.lintcode.com/problem/closest-binary-search-tree-value-ii/description
 *
 * f: 3
 * Created by brianzhang on 11/12/20.
 */
public class ClosestBinarySearchTreeValueII272 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(4);
        for(int i : closestKValues(root, 5, 3)) System.out.println(i);
    }

    // In-Order traverse
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root; // used to traverse the tree
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                if(queue.size() < k){
                    queue.offer(curr.val);
                }else{
                    if(Math.abs(queue.peek() - target) > Math.abs(curr.val - target)){
                        queue.poll();
                        queue.offer(curr.val);
                    }else{
                        break;
                    }
                }
                curr = curr.right;
            }
        }

        return (List<Integer>) queue;
    }
}

