package linkedin.vo.my2022VO.dataStructure.singleLinkedList;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
 *
 * bucket array[Single linkedList]
 *
 * Questions which can be asked to the interviewer before implementing the solution
     * For simplicity, are the keys integers only?
     * For collision resolution, can we use chaining?
     * Do we have to worry about load factors?
     * Can we assume inputs are valid or do we have to validate them?
     * Can we assume this fits memory?
 *
 * Created by brianzhang on 11/3/19.
 */
public class DesignHashMap {
    public static void main(String[] args) {
        DesignHashMap test = new DesignHashMap();
        test.put(1, 1);
        System.out.println(test.get(1));
        test.put(2,2);
        test.remove(2);
    }

    class ListNode {
        int key, val;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    ListNode[] nodes = new ListNode[10001];

    // 关键的utility方法: return previous node of target node with key val
    private ListNode findElement(int key) {
        int index = getIndex(key); // get location in bucket
        if(nodes[index] == null){
            return nodes[index] = new ListNode(-1, -1);  // create a dummy head node and return if bucket is empty
        }
        ListNode prev = nodes[index];
        while(prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    public int get(int key) {
        ListNode prev = findElement(key);
        return prev.next == null ? -1 : prev.next.val;
    }

    public void put(int key, int value) {
        ListNode prev = findElement(key);
        if (prev.next == null) // empty bucket in array Or prev is the tail node
            prev.next = new ListNode(key, value); // New Node link to dummyHead Or append to the tail node
        else
            prev.next.val = value; // the same key node is existing, so just need override the node value
    }

    public void remove(int key) {
        ListNode prev = findElement(key);
        if(prev.next != null)
            prev.next = prev.next.next;
    }

    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }
}

