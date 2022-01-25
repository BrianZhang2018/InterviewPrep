package linkedin.vo.DLLOone;

import java.util.*;

/**
 * https://leetcode.com/problems/all-oone-data-structure/
 * http://cqbbshuashua.blogspot.com/2018/04/432-all-oone-data-structure.html
 *
 * the difference compare with MaxStack:
 * Maintain a descending ordered LinkedList since we need support getMax and getMin function.
 *
 *  map + linkedList
 *
 * e.g. dummyHead->5->4->3->2->tail
 *
 * Created by brianzhang on 11/23/20.
 */
public class AllOoneDataStructure {
    public static void main(String[] args) {
        AllOoneDataStructure test = new AllOoneDataStructure();
        test.inc("hello");
        test.inc("goodbye");
        test.inc("hello");
        test.inc("hello");
        System.out.println(test.getMaxKey());
        test.inc("leet");
        test.inc("leet");
        test.dec("hello");
        test.inc("leet");
        System.out.println(test.getMaxKey());
    }

    Map<String, DDLNode> map = new HashMap<>();
    DoubleLinkedList dll = new DoubleLinkedList();
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            DDLNode node = new DDLNode(1, key);
            map.put(key, node);
            dll.insert(node, dll.tail);
        } else {
            DDLNode node = map.get(key);
            node.val++;
            dll.moveForward(node);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            DDLNode node = map.get(key);
            if (node.val == 1)
                dll.remove(node);
            else{
                node.val--;
                dll.moveBackward(node);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (map.isEmpty()) return "";
        else
            return dll.head.next.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (map.isEmpty()) return "";
        else
            return dll.tail.prev.key;
    }
}

class DDLNode {
    int val;
    String key;
    DDLNode prev, next;
    public DDLNode(int val, String key) {
        this.val = val;
        this.key = key;
    }
}

class DoubleLinkedList {
    DDLNode head, tail;
    public DoubleLinkedList() {
        head = new DDLNode(0, "");
        tail = new DDLNode(0, "");
        head.next = tail;
        tail.prev = head;
    }

    public void remove(DDLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null; node.prev = null;// can skip this also
    }

    // front->back
    public void insert(DDLNode front, DDLNode back) {
        back.prev.next = front;
        front.prev = back.prev;
        front.next = back;
        back.prev = front;
    }

    public void moveForward(DDLNode node) { // when count increase
        while (node.prev != head && node.val > node.prev.val) {
            remove(node);
            insert(node, node.prev);
            //swapNodes(node, node.prev); // not working here as the map hold the node still point old one after swap, but remove doesn't have this problem
        }
    }

    public void moveBackward(DDLNode node) { // when count decrease
        while (node.next != tail && node.val < node.next.val) {
            DDLNode next = node.next; // note: Since next line will remove "node.next", so the curr node.next will point to "node.next.next", so have to keep the original "node.next" this line.
            remove(node.next);
            insert(next, node);
            //swapNodes(node.next, node);
        }
    }

    /*public void swapNodes (DDLNode node1, DDLNode node2) {
        int val = node2.val;
        String key = node2.key;
        node2.key = node1.key;
        node2.val = node1.val;
        node1.key = key;
        node1.val = val;
    }*/
}
