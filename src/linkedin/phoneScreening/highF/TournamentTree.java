package linkedin.phoneScreening.highF;

import model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 *
 * Created by brianzhang on 10/25/20.
 */
public class TournamentTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int secondMin = Integer.MAX_VALUE;
        int min = root.val;

        boolean found =false;

        while(!queue.isEmpty()){
            TreeNode tn = queue.poll();
            if(tn.val > min) {
                secondMin = Math.min(tn.val, secondMin);
                found = true;
                continue;
            }

            if(tn.left != null) {
                queue.add(tn.left);
                queue.add(tn.right);
            }
        }

        return found ? secondMin : -1;
    }
}
