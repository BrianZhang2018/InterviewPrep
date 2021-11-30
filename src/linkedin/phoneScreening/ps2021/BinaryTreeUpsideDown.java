package linkedin.phoneScreening.ps2021;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-upside-down/
 *
 * Created by brianzhang on 8/11/21.
 */
public class BinaryTreeUpsideDown {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        upsideDownBinaryTreeIterative(root);
    }

    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;

        TreeNode newRoot =  upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }

    public static TreeNode upsideDownBinaryTreeIterative(TreeNode root) {
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            next = curr.left;

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
