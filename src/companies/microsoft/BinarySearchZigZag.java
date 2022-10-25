package companies.microsoft;

import category.model.TreeNode;

import java.util.*;

public class BinarySearchZigZag {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int nodes = queue.size();
            List<Integer> currLevel = new ArrayList();
            
            for(int i = 0; i<nodes; i++){
                TreeNode node = queue.pop();
                //even level - we store the value as reverse order (from right to left)
                if(res.size() % 2 != 0){
                    currLevel.add(0, node.val);
                }else{
                    currLevel.add(node.val);
                }
                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            
            res.add(currLevel);
        }
        return res;
     }
}