package datadog;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-630278-1-1.html
 * https://www.1point3acres.com/bbs/thread-680807-1-1.html
 *
 * 给一个多叉树，每个节点上都有自己的分数，问从根节点到最终叶子节点累计分数最大值是多少。DFS or BFS
 *
 * Created by brianzhang on 5/6/21.
 */
public class NaryMaxSumPath{
    public static void main(String[] args) {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(2));
        root.children.add(new Node(2));
        root.children.add(new Node(2));
        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(12));
        root.children.get(1).children.add(new Node(10));
        root.children.get(2).children.add(new Node(11));

        findMaxSumPathFromRootToLeaf(root);
        System.out.println(max);
        System.out.println(Arrays.toString(sumPath.toArray()));
    }

    static int max = 0;
    static List<Integer> sumPath;
    public static void findMaxSumPathFromRootToLeaf(Node root){
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, root.val, path);
    }

    public static void dfs(Node node, int sum, List<Integer> path){
        if(node.children.size() == 0) {
            if(max < sum){
                max = sum;
                sumPath = new ArrayList<>(path);
            }
            return;
        }

        for(Node n : node.children){
            path.add(n.val);
            dfs(n, sum + n.val, path);
            path.remove(path.size()-1);
        }
    }
    // bfs, need Pair<Node, Path> to record the path for current node
}

// n-ary tree
class Node {
    public int val;
    public List<Node> children = new ArrayList<>();

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
