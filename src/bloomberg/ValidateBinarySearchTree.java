package bloomberg;


import model.TreeNode;

/**
 * Created by brianzhang on 5/10/20.
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

        return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean helper(TreeNode root, long max, long min){

        if(root == null)
            return true;

        if(root.val >= max || root.val <= min ){
            return false;
        }

        boolean left = helper(root.left, root.val, min);
        boolean right = helper(root.right, max, root.val);

        return left && right;
    }
}
