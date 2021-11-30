package linkedin.vo.DLLOone;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/all-oone-data-structure/
 * http://cqbbshuashua.blogspot.com/2018/04/432-all-oone-data-structure.html
 *
 * the difference compare with MaxStack:
 * 1. key is string, so use Map<String, DDLNode>
 * 2. Maintain an ordered LinkedList
 *
 * Created by brianzhang on 11/23/20.
 */
public class AllOoneDataStructure {
    public static void main(String[] args) {
        AllOoneDataStructure test = new AllOoneDataStructure();
        test.inc("test1");
        test.inc("test1");
        test.inc("test2");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
        test.inc("test2");
        test.inc("test2");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
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
        else return dll.head.next.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (map.isEmpty()) return "";
        else return dll.tail.prev.key;
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
    }

    // front->back
    public void insert(DDLNode front, DDLNode back) {
        back.prev.next = front;
        front.prev = back.prev;
        front.next = back;
        back.prev = front;
    }

    public void moveForward(DDLNode node) {
        while (node.prev != head && node.val > node.prev.val) {
            remove(node);
            insert(node, node.prev);
        }
    }

    public void moveBackward(DDLNode node) {
        while (node.next != tail && node.val < node.next.val) {
            DDLNode next = node.next; // be careful here
            remove(node.next);
            insert(next, node);
        }
    }
}
