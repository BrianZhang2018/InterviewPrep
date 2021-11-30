package qualtrics;

import model.TreeNode;
import java.util.*;

/**
 * freq: 2
 * Created by brianzhang on 1/13/21.
 */
public class BinaryTreeZigzagLevelOrderTraversal103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int level = 1;
        while(!queue.isEmpty()){
            List<Integer> temp = new ArrayList();
            int size = queue.size();
            TreeNode node;
            for(int i=0; i<size; i++){
                node = queue.poll();
                if(level % 2 != 0){
                    temp.add(node.val);
                }else{
                    temp.add(0, node.val);
                }
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(temp);
            level++;
        }

        return res;
    }
}
