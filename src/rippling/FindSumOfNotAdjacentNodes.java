package rippling;

/**
 * https://www.geeksforgeeks.org/maximum-sum-nodes-binary-tree-no-two-adjacent/
 */
public class FindSumOfNotAdjacentNodes {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);
        root.left.left = new Node(1);

        System.out.print(new FindSumOfNotAdjacentNodes().solution(root));
    }

    public int solution(Node root) {
        SubTreeSum res = dfs(root);
        return Math.max(res.excl, res.incl);
    }

    public SubTreeSum dfs(Node root) {
        if(root == null) {
            return new SubTreeSum(0, 0);
        }

        SubTreeSum left = dfs(root.left);
        SubTreeSum right = dfs(root.right);

        // root node is included (adjacent Left and right children are not included)
        int incl = root.value + left.excl + right.excl;
        // root node is excluded (Either left or right child is included or excl)
        int excl = Math.max(left.incl, left.excl) + Math.max(right.incl, right.excl);

        return new SubTreeSum(incl, excl);
    }
}

class SubTreeSum {
    int incl, excl;
    public SubTreeSum(int incl, int excl) {
        this.incl= incl;
        this.excl = excl;
    }
}

class Node
{
    int value;
    Node left, right;
    Node(int data)
    {
        this.value=data;
        left=right=null;
    }
};