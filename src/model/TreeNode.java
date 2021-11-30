package model;

/**
 * Created by brianzhang on 7/18/18.
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public boolean isAsterisk;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int x, boolean isAsterisk) {
        val = x;
        this.isAsterisk = isAsterisk;
    }
}

