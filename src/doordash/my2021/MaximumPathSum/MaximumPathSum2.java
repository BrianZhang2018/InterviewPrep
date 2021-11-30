package doordash.my2021.MaximumPathSum;

import model.TreeNode;
import java.util.*;

/**
 * the maximum path sum between two asterisk nodes which can on any node
 *
 # Example Tree 2
 #       500*
 #    /        \  
 #   *2        80
 #  /  \      /   \
 # *100 *50  *200  151
                     \
                     100*

 # ans = 831

 * 递归,自底向上. 从底往上看，不要从上往下看节点的答案, 底下的小问题递向上层层递推出问题.
 *
 * Created by brianzhang on 11/19/21.
 */
public class MaximumPathSum2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(500, true);
        root.left = new TreeNode(2, true);
        root.right = new TreeNode(80, false);
        root.left.left = new TreeNode(100, true);
        root.left.right = new TreeNode(50, true);
        root.right.left = new TreeNode(200, true);
        root.right.right = new TreeNode(151, false);
        root.right.right.right = new TreeNode(100, true);

        dfs(root);
        System.out.println(maxPath);
    }

    static int maxPath = Integer.MIN_VALUE;
    public static SubPathSum dfs(TreeNode root) {
        if(root == null) return null;

        SubPathSum left = dfs(root.left);
        SubPathSum right = dfs(root.right);
        left = left == null? new SubPathSum(0, false, new ArrayList<>()) : left;
        right = right == null? new SubPathSum(0, false, new ArrayList<>()) : right;

        if(root.isAsterisk) {

            if(left.isStartWithAsterisk && right.isStartWithAsterisk) {
                maxPath = Math.max(root.val + Math.max(left.sum, right.sum), maxPath);
            }else if (left.isStartWithAsterisk || right.isStartWithAsterisk){
                maxPath = Math.max(root.val + (left.isStartWithAsterisk ? left.sum : right.sum), maxPath);
            }
            return new SubPathSum(root.val, true);  // 剪枝
        }else{ // !root.isAsterisk

            if(left.isStartWithAsterisk && right.isStartWithAsterisk){
                maxPath = Math.max(root.val + left.sum + right.sum, maxPath);
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), true);
            } else if (left.isStartWithAsterisk || right.isStartWithAsterisk) {
                return new SubPathSum(root.val + (left.isStartWithAsterisk ? left.sum: right.sum), true); // 记住是自底向上看问题,当递归回到中间的节点which problem can be solved base on 已解决的下层节点
            }else{ // if no asterisk
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), false);
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