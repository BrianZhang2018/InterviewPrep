package companies.amazon;

import companies.amazon.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 7/16/18.
 */
class TwoNodeSum {
    public static void main(String[] args) {
        TwoNodeSum twoNodeSum = new TwoNodeSum();
        //twoNodeSum.findTarget(root, )
    }

    private List<Integer> list = new ArrayList();

    public boolean findTarget(TreeNode root, int k) {

        inOrderTraverse(root);

        if (list.size() < 3) {
            return false;
        }

        int start = 0;
        int end = list.size() - 1;

        while (start < end) {

            if (list.get(start) + list.get(end) == k) {
                return true;
            }

            if (list.get(start) + list.get(end) > k) {
                end--;
            }

            if (list.get(start) + list.get(end) < k) {
                start++;
            }
        }
        return false;
    }

    public void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        inOrderTraverse(root.right);
        list.add(root.val);
        inOrderTraverse(root.left);
    }

}

