package doordash.my2021.MaximumPathSum;

import model.TreeNode;
import java.util.*;

/**
 * Print out the Maximum sum path
 # Example Tree 2
 #          500*
 #       /        \
 #     *2          80
 #    /   \      /    \
 # *100 *50   *200    151
                         \
                         100*
 # ans = 831
 * Created by brianzhang on 11/27/21.
 */
public class MaximumPathSumPrintOut {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(500, false);
        root.left = new TreeNode(2, true);
        root.right = new TreeNode(80, true);
        root.left.left = new TreeNode(100, true);
        root.left.right = new TreeNode(50, true);
        root.right.left = new TreeNode(400, true);
        root.right.left.left= new TreeNode(100, true);
        root.right.right = new TreeNode(151, false);
        root.right.right.right = new TreeNode(100, true);

        dfs(root);
        System.out.println(maxPathSum);
        System.out.println(Arrays.toString(maxPath.toArray()));
    }

    static int maxPathSum = Integer.MIN_VALUE;
    static List<Integer> maxPath = new ArrayList<>();
    public static SubPathSum dfs(TreeNode root) {
        if(root == null) return null;
        SubPathSum left = dfs(root.left);
        SubPathSum right = dfs(root.right);
        left = left == null? new SubPathSum(0, false, new ArrayList<>()) : left;
        right = right == null? new SubPathSum(0, false, new ArrayList<>()) : right;

        if(root.isAsterisk) {
            if(left.isStartWithAsterisk && right.isStartWithAsterisk) {
                if(root.val + Math.max(left.sum, right.sum) > maxPathSum) {
                    maxPathSum = root.val + Math.max(left.sum, right.sum);
                    List<Integer> currPath = left.sum > right.sum? left.path : right.path;
                    currPath.add(root.val);
                    maxPath = currPath;
                }
            }else if (left.isStartWithAsterisk || right.isStartWithAsterisk){
                if(root.val + (left.isStartWithAsterisk ? left.sum : right.sum) > maxPathSum) {
                    maxPathSum = root.val + (left.isStartWithAsterisk ? left.sum : right.sum);
                    List<Integer> currPath = left.isStartWithAsterisk ? left.path : right.path;
                    currPath.add(root.val);
                    maxPath = currPath;
                }
            }
            return new SubPathSum(root.val, true, new ArrayList(Arrays.asList(root.val)));  // 剪枝 or leaf node
        }else{ // !root.isAsterisk
            if(left.isStartWithAsterisk && right.isStartWithAsterisk){
                if(root.val + left.sum + right.sum > maxPathSum) {
                    maxPathSum = root.val + left.sum + right.sum;
                    maxPath.clear();
                    maxPath.addAll(left.path);
                    maxPath.add(root.val);
                    maxPath.addAll(right.path);
                }
                List<Integer> currPath = left.sum > right.sum? left.path : right.path;
                currPath.add(root.val);
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), true, currPath);
            } else if (left.isStartWithAsterisk || right.isStartWithAsterisk) {
                List<Integer> currPath = left.isStartWithAsterisk ? left.path : right.path;
                currPath.add(root.val);
                return new SubPathSum(root.val + (left.isStartWithAsterisk ? left.sum: right.sum), true, currPath); // 记住是自底向上看问题,当递归回到中间的节点which problem can be solved base on 已解决的下层节点
            }/*else{ // if no asterisk, just cut it, we don't need to care it at all }*/

            return null;
        }
    }
}

