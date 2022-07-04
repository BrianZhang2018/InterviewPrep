package linkedin.vo.my2022VO.dataStructure.DLLPlusMap;

import java.util.*;

// double linked list for fast insert/deletion
// keep track of head and tail
// node contains keys which has same count

// 1. Map<Key, DllNode>
// 2. Double linkedList:
// dummyHead -> count1-node(keys) -> count2-node(keys) -> count3-node(keys)... count1 means string appear 1 time, count2 appear 2 times

class AllOOneFinal {
    public static void main(String[] args) {
        AllOOneFinal test = new AllOOneFinal();
        test.inc("hello");
        test.inc("hello");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
        test.inc("leet");
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
    }

    class LNode {
        LNode prev, next;
        int count;
        Set<String> keys = new LinkedHashSet<String>();
        public LNode(String key, int count) {
            this.keys.add(key);
            this.count = count;
        }
        public LNode(int count) {
            this.count = count;
        }
    }

    Map<String, LNode> map = new HashMap<String, LNode>();
    LNode head, tail;

    public AllOOneFinal() {
        head = new LNode(-1);
        tail = new LNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        LNode dllLNode = null;
        if(!map.containsKey(key)) {
            if(head.next.count == 1) {
                dllLNode = head.next;
                dllLNode.keys.add(key);
            }else{
                dllLNode = new LNode(key, 1);
                insert(dllLNode, head, head.next);
            }
        }else{
            LNode LNode = map.get(key);
            if(LNode.next.count == LNode.count +1) {
                dllLNode = LNode.next;
                dllLNode.keys.add(key);
            }else{
                dllLNode = new LNode(key, LNode.count + 1);
                insert(dllLNode, LNode, LNode.next);
            }
            LNode.keys.remove(key); // be careful
            if(LNode.keys.isEmpty()) remove(LNode);
        }

        map.put(key, dllLNode);
    }

    public void dec(String key) {
        if(!map.containsKey(key)) return;

        LNode n = map.get(key);
        n.keys.remove(key);
        if(n.count == 1) {
            map.remove(key);
        }else{
            LNode dllLNode = null;
            if(n.prev.count == n.count-1) {
                dllLNode = n.prev;
                dllLNode.keys.add(key);
            }else{
                dllLNode = new LNode(key, n.count-1);
                insert(dllLNode, n.prev, n);
            }
            if(n.keys.isEmpty()) remove(n);
            map.put(key, dllLNode);
        }

        if(n.keys.isEmpty()) remove(n);
    }

    public String getMaxKey() {
        if(tail.prev == head) return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if(head.next == tail)  return "";
        return head.next.keys.iterator().next();
    }

    public void insert(LNode n, LNode prev, LNode next) {
        prev.next = n;
        n.prev = prev;
        n.next = next;
        next.prev = n;
    }

    public void remove(LNode n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
}
