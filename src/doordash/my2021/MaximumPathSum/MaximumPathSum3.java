package doordash.my2021.MaximumPathSum;

import model.TreeNode;
import java.util.*;

/**
 * Print out the Maximum sum path
 *
 * Created by brianzhang on 11/27/21.
 */
public class MaximumPathSum3 {
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
        System.out.println(Arrays.toString(maxPathNodes.toArray()));
    }

    static int maxPath = Integer.MIN_VALUE;
    static List<Integer> maxPathNodes;
    public static SubPathSum dfs(TreeNode root) {
        if(root == null) return null;

        SubPathSum left = dfs(root.left);
        SubPathSum right = dfs(root.right);
        left = left == null? new SubPathSum(0, false, new ArrayList<>()) : left;
        right = right == null? new SubPathSum(0, false, new ArrayList<>()) : right;

        if(root.isAsterisk) {

            if(left.isStartWithAsterisk && right.isStartWithAsterisk) {
                if(root.val + Math.max(left.sum, right.sum) > maxPath) {
                    maxPath = root.val + Math.max(left.sum, right.sum);
                    List<Integer> currPath = left.sum > right.sum? left.path : right.path;
                    currPath.add(root.val);
                    maxPathNodes = currPath;
                }
            }else if (left.isStartWithAsterisk || right.isStartWithAsterisk){
                if(root.val + (left.isStartWithAsterisk ? left.sum : right.sum) > maxPath) {
                    maxPath = root.val + (left.isStartWithAsterisk ? left.sum : right.sum);
                    List<Integer> currPath = left.isStartWithAsterisk ? left.path : right.path;
                    currPath.add(root.val);
                    maxPathNodes = currPath;
                }
            }
            return new SubPathSum(root.val, true, new ArrayList(Arrays.asList(root.val)));  // 剪枝

        }else{ // !root.isAsterisk

            if(left.isStartWithAsterisk && right.isStartWithAsterisk){
                maxPath = Math.max(root.val + left.sum + right.sum, maxPath);
                List<Integer> currPath = left.sum > right.sum? left.path : right.path;
                currPath.add(root.val);
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), true, currPath);
            } else if (left.isStartWithAsterisk || right.isStartWithAsterisk) {
                List<Integer> currPath = left.isStartWithAsterisk ? left.path : right.path;
                currPath.add(root.val);
                return new SubPathSum(root.val + (left.isStartWithAsterisk ? left.sum: right.sum), true, currPath); // 记住是自底向上看问题,当递归回到中间的节点which problem can be solved base on 已解决的下层节点
            }else{ // if no asterisk
                return new SubPathSum(root.val + Math.max(left.sum, right.sum), false, new ArrayList(Arrays.asList(root.val)));
            }
        }
    }
}

