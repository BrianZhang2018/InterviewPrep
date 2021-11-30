package linkedin.vo;

import category.model.TreeNode;
import java.util.*;

/**
 * Use the binaryTree height as the index to collect the nodes, the leave node's height = 0
 *
 * Linkedin
 * https://www.lintcode.com/problem/find-leaves-of-binary-tree/note/191875
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
    
    public static int dfs(TreeNode root, List<List<Integer>> res){
        if(root == null) return -1; // "-1"
            
        int left  = dfs(root.left, res);
        int right = dfs(root.right, res);
        System.out.println("left: " + left + " , " + "right: " + right);
        
        int height = Math.max(left, right) + 1; // leave node height is "0"

        if(res.size() == height){
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        
        return height;
    }
}