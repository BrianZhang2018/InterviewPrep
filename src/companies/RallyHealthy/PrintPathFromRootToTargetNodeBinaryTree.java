package companies.RallyHealthy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/
 * dfsHelper + backtracking
 *
 * Time complexity: O(n) in worst case, where n is the number of nodes in the binary tree.
 * Created by brianzhang on 3/19/20.
 */
public class PrintPathFromRootToTargetNodeBinaryTree {

    public static void main(String args[]) {
        Node root = new Node(10);
        root.left = new Node(6);
        root.right = new Node(21);
        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.right.left = new Node(13);
        root.right.right = new Node(25);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(18);

        PrintPathFromRootToTargetNodeBinaryTree dfs = new PrintPathFromRootToTargetNodeBinaryTree();
        dfs.findPath(root, new Node(25));
    }

    public void findPath(Node start, Node target) {

        for (Node node : helper(new ArrayList<>(), start, target)) {
            System.out.println(node.val);
        }
    }

    public List<Node> helper(List<Node> temp, Node curr, Node target) {
        if (curr == null)
            return null;

        temp.add(curr);
        if (curr.val == target.val) {
            return new ArrayList<>(temp); //clone copy
        }

        List<Node> left = helper(temp, curr.left, target);
        List<Node> right = helper(temp, curr.right, target);
        if (left != null)
            return left;
        if (right != null)
            return right;

        temp.remove(temp.size() - 1); // missed backtracking step in my first solution
        return null;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int value) {
        val = value;
        left = right = null;
    }
}