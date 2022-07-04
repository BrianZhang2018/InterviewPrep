package linkedin.vo.others;

import model.TreeNode;
import java.util.*;

/**
 * https://www.lintcode.com/problem/find-leaves-of-binary-tree/description
 *
 * f: 3
 * Created by brianzhang on 11/17/20.
 */
public class FindBinaryLeaves366 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        FindBinaryLeaves366 fl = new FindBinaryLeaves366();
        for (List<Integer> l : fl.findLeaves(root)) System.out.println(Arrays.toString(l.toArray()));
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root);
        return result;
    }
    // key point: return level
    private int helper(List<List<Integer>> list, TreeNode root){
        if(root==null) return -1;

        int left = helper(list, root.left);
        int right = helper(list, root.right);
        int curr = Math.max(left, right) + 1;

        // the first time reached here when curr==0, since the tree is bottom-up processed.
        if(list.size() <= curr){
            list.add(new ArrayList<>());
        }

        list.get(curr).add(root.val);
        return curr;
    }
}
