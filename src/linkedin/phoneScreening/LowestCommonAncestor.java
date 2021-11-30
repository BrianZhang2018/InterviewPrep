package linkedin.phoneScreening;

import model.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Created by brianzhang on 11/11/20.
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) return null;

        while(root != null) {
            if(root.val > p.val && root.val > q.val){
                root = root.left;
            }else if(root.val < p.val && root.val < q.val) {
                root = root.right;
            }else {  // 找到劈叉处
                return root;
            }
        }

        return null;
    }
}
