package doordash.my2021.MaximumPathSum;

import model.TreeNode;
import java.util.*;

/**
 * the maximum path sum between two asterisk nodes which can on any node
 # Example Tree 2
 #          500*
 #       /        \
 #     *2          80
 #    /   \      /    \
 # *100 *50   *200    151
                        \
                        100*
 # ans = 831
 * bottom-up recursion: 递归,自底向上. 从底往上看，不要从上往下看节点的答案, 底下的小问题递向上层层递推出问题.
 *
 * Created by brianzhang on 11/19/21.
 */
public class MaximumPathSumBetweenTwoAsteriskNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(500, false);
        root.left = new TreeNode(2, false);
        root.right = new TreeNode(80, false);
        root.left.left = new TreeNode(100, true);
        root.left.right = new TreeNode(50, false);
        root.right.left = new TreeNode(200, false);
        root.right.right = new TreeNode(151, false);
        root.right.right.right = new TreeNode(100, true);
        dfs(root);
        System.out.println(maxPathSum);
    }

    static int maxPathSum = Integer.MIN_VALUE;
    public static SubPathSum dfs(TreeNode root) {
        if(root == null) return null;
        SubPathSum left = dfs(root.left);
        SubPathSum right = dfs(root.right);
        left = left == null? new SubPathSum(0, false) : left;
        right = right == null? new SubPathSum(0, false) : right;
        if(root.isAsterisk) {
            if(left.isStartWithAsterisk && right.isStartWithAsterisk) {
                maxPathSum = Math.max(root.val + Math.max(left.sum, right.sum), maxPathSum);
            }else if (left.isStartWithAsterisk || right.isStartWithAsterisk){
                maxPathSum = Math.max(root.val + (left.isStartWithAsterisk ? left.sum : right.sum), maxPathSum);
            }
            return new SubPathSum(root.val, true);  // 剪枝 or leaf node
        }else{ // no Asterisk
            if(left.isStartWithAsterisk && right.isStartWithAsterisk){
                maxPathSum = Math.max(root.val + left.sum + right.sum, maxPathSum);
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), true);
            } else if (left.isStartWithAsterisk || right.isStartWithAsterisk) {
                return new SubPathSum(root.val + (left.isStartWithAsterisk ? left.sum: right.sum), true); // 记住是自底向上看问题,当递归回到中间的节点which problem already can be solved base on已解决的下层节点
            } else{ // if no asterisk, just Cut it, don't need to care it at all
                return null;
            }
        }
    }
}

class SubPathSum {
    int sum;
    boolean isStartWithAsterisk;
    List<Integer> path;
    public SubPathSum(int val, boolean hasAsterisk) {
        this.sum = val;
        this.isStartWithAsterisk = hasAsterisk;
    }
    public SubPathSum(int val, boolean hasAsterisk, List<Integer> path) {
        this.sum = val;
        this.isStartWithAsterisk = hasAsterisk;
        this.path = path;
    }
}