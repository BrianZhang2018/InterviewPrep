package linkedin.phoneScreening.ps2021;

import model.TreeNode;
import java.util.*;

/**
 * Time Complexity: Assuming N is the total number of nodes in the binary tree, traversing the tree takes O(N) time
 * and storing all the pairs at the correct position also takes O(N) time. Hence overall time complexity of this approach is O(N).
 *
 * Space Complexity: O(N), the space used by solution array.
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        dfs(root, res);
        return res;
    }

    public int dfs(TreeNode root, List<List<Integer>> res) {
        if(root == null) return -1;

        int level = Math.max(dfs(root.right, res), dfs(root.left, res)) +1;
        if(res.size() == level) {
            res.add(new ArrayList());
        }

        res.get(level).add(root.val);
        return level;
    }
}
