package crowdStrike;

public class ImplementQueueSingleLinkedList {
    public static void main(String[] args) {
        ImplementQueueSingleLinkedList test = new ImplementQueueSingleLinkedList();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
    }

    Node front, rear;
    public ImplementQueueSingleLinkedList() {
        front = new Node(-1);
        rear = front;
    }

    public boolean add(int val) {
        Node n = new Node(val);
        rear.next = n;
        rear = n;
        return true;
    }

    public int poll() {
        if(front.next == null)  return -1;

        int val = front.next.val;
        front.next =front.next.next;
        if(front.next == null){
            rear = front;
        }
        return val;
    }
}

class Node {
    public Node next;
    public int val;
    public Node(int val) {
        this.val = val;
    }
}