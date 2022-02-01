package linkedin.vo.my2022VO.dataStructure.singleLinkedList;

/**
 * https://leetcode.com/problems/design-hashmap/discuss/152746/Java-Solution
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
    ListNode[] nodes = new ListNode[10001];

    public int get(int key) {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);
        return prev.next == null ? -1 : prev.next.val;
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);

        if (prev.next == null)
            prev.next = new ListNode(key, value);
        else
            prev.next.val = value;
    }

    public void remove(int key) {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);

        if(prev.next != null)
            prev.next = prev.next.next;
    }

    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode findElement(int index, int key) {
        if(nodes[index] == null)
            return nodes[index] = new ListNode(-1, -1);

        ListNode prev = nodes[index];
        while(prev.next != null && prev.next.key != key)
        {
            prev = prev.next;
        }
        return prev;
    }

    private static class ListNode {
        int key, val;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

