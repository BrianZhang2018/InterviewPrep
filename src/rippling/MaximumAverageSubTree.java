package rippling;

import model.TreeNode;

public class MaximumAverageSubTree {
    public static void main(String[] args) {

    }
    double res = Long.MIN_VALUE;
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return res;
    }
    public SubtreeRes dfs(TreeNode root) {
        if(root == null)
            return new SubtreeRes(0, 0);

        SubtreeRes left = dfs(root.left);
        SubtreeRes right = dfs(root.right);
        SubtreeRes curr = new SubtreeRes(1, root.val);

        if(left.numOfNodes > 0) {
            curr.numOfNodes +=left.numOfNodes;
            curr.sum+=left.sum;
        }
        if(right.numOfNodes > 0) {
            curr.numOfNodes +=right.numOfNodes;
            curr.sum+=right.sum;
        }

        res = Math.max(curr.sum/(double)curr.numOfNodes, res);
        return curr;
    }
}

class SubtreeRes {
    int numOfNodes;
    int sum;
    public SubtreeRes(int numOfNodes, int sum) {
        this.numOfNodes = numOfNodes;
        this.sum = sum;
    }
}
