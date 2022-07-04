package linkedin.vo.others;

import category.model.TreeNode;
import java.util.*;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 *
 * Use the binaryTree height as the index to collect the nodes, the leaf node's height = 0
 * For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
 * The height of a node is the number of edges from the node to the deepest leaf. So, the height of leaf node is 0.
 *
 * I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
 * The height of a node is also its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.
 *
 * added one line node.left = node.right = null; to remove visited nodes
 */
public class FindLeavesOfBinaryTree{
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        for(List<Integer> l : findLeaves(root)) System.out.println(Arrays.toString(l.toArray()));
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    
    public static int dfs(TreeNode node, List<List<Integer>> res){
        if(node == null) return 0; // note
            
        int left  = dfs(node.left, res), right = dfs(node.right, res);
        System.out.println("left: " + left + " , " + "right: " + right);
        int height = Math.max(left, right) ; // leaf node height is "0"
        if(res.size() == height) res.add(new ArrayList<>());
        res.get(height).add(node.val);

        node.right = node.left = null;
        return height + 1;
    }
}