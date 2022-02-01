package linkedin.vo.my2022VO.dataStructure.singleLinkedList;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
 *
 * Some of the questions which can be asked to the interviewer before implementing the solution
 *
     * For simplicity, are the keys integers only?
     * For collision resolution, can we use chaining?
     * Do we have to worry about load factors?
     * Can we assume inputs are valid or do we have to validate them?
     * Can we assume this fits memory?
 *
 * Apple phone screen interview
 * Created by brianzhang on 11/3/19.
 */
public class DesignHashMapWithGenericType<T> {
    public static void main(String[] args) {
        DesignHashMapWithGenericType test = new DesignHashMapWithGenericType();
        test.put(1, 1);
        System.out.println(test.get(1));
        test.put(2,2);
        System.out.println(test.remove(2));
    }

    class ListNode<T> {
        T key, val;
        ListNode next;

        ListNode(T key, T val) {
            this.key = key;
            this.val = val;
        }
    }

    final ListNode[] nodes = new ListNode[10001];

    public void put(T key, T value) {
        int index = hash(key);
        ListNode prev = findNode(index, key);
        if (prev.next == null){     // empty bucket in array
            prev.next = new ListNode(key, value); // dummy headNode(-1,-1) -> New Node
        } else{   // means the same key node already existing, so just need override the node value
            prev.next.val = value;
        }
    }

    public T get(T key) {
        int index = hash(key);
        ListNode prev = findNode(index, key);
        return prev.next == null ? null : (T)prev.next.val;
    }

    public T remove(T key) {
        int index = hash(key);

        ListNode prev = findNode(index, key);
        if (prev.next == null) return null;

        T removed = (T)prev.next.val;
        prev.next = prev.next.next;

        return removed;
    }

    // return "Previous" node of the target node (key)
    // 1. if bucket is empty, return dummy head node 2. if bucket exist, return previous node of target node
    private ListNode findNode(int index, T key) {
        if(nodes[index] == null) return nodes[index] = new ListNode(-1, -1); // create a dummy head node and return if bucket is empty

        ListNode prev = nodes[index];
        while (prev != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    // generate hash key
    private int hash(T key) {
        return Integer.hashCode((Integer)key) % nodes.length;
    }
}

