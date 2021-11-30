package doordash.my2021.MaximumPathSum;

import model.TreeNode;

/**
 * the maximum path sum between two asterisk node which asterisk only leaf node
 *
 *  Example Tree 1
 #         5
 #     /      \  
 #    2        80
 #  /   \     /   \
 # *100 *50  *100  *15
 # ans = 287 = 100 + 2 + 5 + 80 + 100

 * Created by brianzhang on 11/21/21.
 */
public class MaximumPathSum1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(80);
        root.left.left = new TreeNode(100, true);
        root.left.right = new TreeNode(50, true);
        root.right.left = new TreeNode(100, true);
        root.right.right = new TreeNode(15, true);

        dfs(root);
        System.out.println(maxPath);
    }

    static int maxPath = Integer.MIN_VALUE;
    public static int dfs(TreeNode root) {
        if(root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        maxPath = Math.max(left + right+ root.val, maxPath);

        return root.val + Math.max(left, right);
    }
}
