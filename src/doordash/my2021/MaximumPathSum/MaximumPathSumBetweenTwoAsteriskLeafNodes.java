package doordash.my2021.MaximumPathSum;

import model.TreeNode;

/**
 * the maximum path sum between two asterisk node which asterisk only leaf node
 *  Example Tree:
 *         5
 *     2       80
 #  100 *50  100 *15
 # ans: 287 = 100 + 2 + 5 + 80 + 100

 * bottom-up recursion
 *
 * Created by brianzhang on 11/21/21.
 */
public class MaximumPathSumBetweenTwoAsteriskLeafNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(80);
        root.left.left = new TreeNode(100, false);
        root.left.right = new TreeNode(50, false);
        root.right.left = new TreeNode(100, false);
        root.right.right = new TreeNode(15, false);

        dfs(root);
        System.out.println(maxPathSum);
    }

    static int maxPathSum = Integer.MIN_VALUE;
    public static int dfs(TreeNode root) {
        if(root == null) return 0;

        if(root.left == null && root.right == null && !root.isAsterisk)
            return Integer.MIN_VALUE;

        int left = dfs(root.left);
        int right = dfs(root.right);

        maxPathSum = Math.max(left + right + root.val, maxPathSum);

        return Math.max(left, right) + root.val;
    }
}



