package amazon.onsite;

import model.TreeNode;

import java.util.*;
/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * Steps:
 * 1. build a undirected graph using tree nodes as vertices, and the parent-child relation as edges
 * 2. do BFS with source vertex (target) to find all vertices with distance K to it.
 *
 * Created by brianzhang on 3/22/21.
 */
public class AllNodesDistanceKInBinaryTree {

    Map<TreeNode, List<TreeNode>> map = new HashMap();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<> ();
        if (root == null || K < 0) return res;
        buildMap(root, null);
        if (!map.containsKey(target)) return res;
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                for (TreeNode next : map.get(node)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
            K--;
        }
        return res;
    }

    private void buildMap(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<>());
            if (parent != null)  {  // build a undirected graph
                map.get(node).add(parent);
                map.get(parent).add(node) ;
            }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }
}
