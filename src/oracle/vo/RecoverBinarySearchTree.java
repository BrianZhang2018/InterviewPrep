package oracle.vo;

import category.Tree.BinaryTree.BinaryTreeUtil;
import category.model.TreeNode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
 *
 * "inorder traversal" can be used to compare the current and previous node as prev always less than curr node in in-order-traversal bst
 *
 * TC: O(N) - N is the number of nodes
 * Space complexity: Recursion stack is O(height), the best case will be O(logN), the worst case is O(N)
 *
 * Created by brianzhang on 5/22/20.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Before: ");BinaryTreeUtil.inOrderPrint(root);
        recoverTree(root);
        System.out.println("After: ");BinaryTreeUtil.inOrderPrint(root);
    }

    static TreeNode firstNode, secondNode;
    static TreeNode prevNode;

    public static void recoverTree(TreeNode root) {
        // In order traversal to find the two nodes
        inorderTraverse(root);

        // Swap the values of the two nodes
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private static void inorderTraverse(TreeNode node) {
        if (node == null) return;

        inorderTraverse(node.left);

        if(prevNode != null){
            if (firstNode == null && prevNode.val >= node.val) {
                firstNode = prevNode;
            }
            if (firstNode != null && prevNode.val >= node.val) {
                // Swap value in binary search tree that must be the large value swap to the front, and corresponding small value go to back.
                // So, we do "firstNode = prevNode" firstly since prevNode is the swapped large value from back,
                // for the small value secondNode, we do "secondNode = node" since root is small one
                secondNode = node;
            }

            // can be simplified to
         /*   if (prevNode.val >= node.val) {
                if(firstNode == null) firstNode = prevNode;
                secondNode = node;
            }*/
        }

        prevNode = node;
        inorderTraverse(node.right);
    }
}
