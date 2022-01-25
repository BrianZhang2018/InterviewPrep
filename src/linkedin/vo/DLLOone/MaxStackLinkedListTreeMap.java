package linkedin.vo.DLLOone;

import java.util.*;

/**
 * https://www.lintcode.com/problem/max-stack/description
 * https://www.cnblogs.com/apanda009/p/7965683.html
 *
 * 1. LinkedList to simulate a stack which reduce the all remove operation to O(1)
 * 2. TreeMap to get max value in nodes, O(logN)
 *
 * e.g.
 * dummyHead->element1->element2->element3->tail
 *
 * Created by brianzhang on 11/26/20.
 */
public class MaxStackLinkedListTreeMap {
    public static void main(String[] args) {
        MaxStackLinkedListTreeMap stack = new MaxStackLinkedListTreeMap();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        stack.push(2);
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.popMax());//  -> 5
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.peekMax()); // -> 5
        System.out.println(stack.popMax()); // -> 5
        System.out.println(stack.peekMax()); // -> 2
    }

    TreeMap<Integer, List<Node>> map = new TreeMap();
    DLL dll = new DLL();

    public MaxStackLinkedListTreeMap() {}

    public void push(int x) {
        Node n = dll.add(x);
        if(!map.containsKey(x)){
            map.put(x, new ArrayList());
        }
        map.get(x).add(n);
    }

    public int pop() {
        return dll.remove();
    }

    public int top() {
        return dll.peek();
    }

    // logN
    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = map.lastKey(); // retrieve the last or the highest key present in the map
        List<Node> list = map.get(max);
        Node node = list.remove(list.size()-1);
        dll.remove(node);

        if(list.size() == 0) map.remove(max);

        return max;
    }
}

class Node{
    Node prev, next;
    int val;
    public Node(int val){
        this.val = val;
    }
}

class DLL{ // double linkedList
    Node head, tail;
    public DLL(){
        head  = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    // insert the new node before "tail" node
    public Node add(int val){
        Node n = new Node(val);
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
        return n;
    }

    public Node remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    public int remove(){ // remove the last node before tail
        return remove(tail.prev).val;
    }

    public int peek(){
        return tail.prev.val;
    }
}