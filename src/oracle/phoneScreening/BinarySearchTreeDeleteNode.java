package oracle.phoneScreening;

import model.TreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 *
 * Created by brianzhang on 11/3/20.
 */
public class BinarySearchTreeDeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return null;
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        }else if ( key > root.val) {
            root.right= deleteNode(root.right, key);
        } else {
            if(root.left == null) {
                return root.right;
            }

            if(root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right, key);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    public TreeNode findMin(TreeNode node, int key){
        while(node.left!= null){
            node = node.left;
        }

        return node;
    }
}
