package bloomberg.myPhonescreening.y2022;

// 2022-01-07
// this is my version which I wrote during the phone interview
public class AddTwoSumLinkedList {

    static Node sum(Node op1, Node op2) {
        Node o1 = reverse(op1);
        Node o2 = reverse(op2);
        Node dummy = new Node(0); // 死在这个dummy head, cao
        Node curr = dummy;
        int carryOver = 0;
        while (o1 != null && o2 != null) {
            int sum = o1.value + o2.value + carryOver;
            carryOver = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            o1 = o1.next;
            o2 = o2.next;
        }

        while (o1 != null) {
            int sum = o1.value + carryOver;
            carryOver = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            o1 = o1.next;
        }

        while (o2 != null) {
            int sum = o2.value + carryOver;
            carryOver = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            o2 = o2.next;
        }

        if (carryOver != 0) {
            curr.next = new Node(carryOver);
        }

        return reverse(dummy.next);
    }

    static Node reverse(Node n) {
        Node prev = null;
        while (n != null) {
            Node next = n.next;
            n.next = prev;
            prev = n;
            n = next;
        }
        return prev;
    }

    static class Node {
        Node next = null;
        int value;

        Node(int value) {
            this.value = value;
        }

        public boolean equals(Node node) {
            return this.value == node.value &&
                    (this.next == node.next || this.next.equals(node.next));
        }

        public String toString() {
            return value + (next == null ? "" : "->" + next.toString());
        }
    }

    static Node getLinkedList(int... values) {
        Node result = null;

        Node current = null;

        for (int value : values) {
            if (current == null) {
                current = new Node(value);
                result = current;

            } else {
                current.next = new Node(value);
                current = current.next;
            }
        }

        return result;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("10".compareTo("1"));
        //1->2->3 + 4->5->6 = 5->7->9
        Node op1 = getLinkedList(1, 2, 3);
        Node op2 = getLinkedList(4, 5, 6);
        Node expectedSum = getLinkedList(5, 7, 9);

        Node sum = sum(op1, op2);
        System.out.println(sum);
        assert (sum.equals(expectedSum));

        //1->2->3 + 5->6 = 1->7->9
        op1 = getLinkedList(1, 2, 3); // 123
        op2 = getLinkedList(5, 6); //  56 +
        expectedSum = getLinkedList(1, 7, 9); // -----
        // 179

        sum = sum(op1, op2);
        System.out.println(sum);
        assert sum.equals(expectedSum);

        //1->5 + 1->7->7 = 1->9->2
        op1 = getLinkedList(1, 5);
        op2 = getLinkedList(1, 7, 7);
        expectedSum = getLinkedList(1, 9, 2);

        sum = sum(op1, op2);
        System.out.println(sum);
        assert (sum.equals(expectedSum));

        //9->9->9 + 1 = 1->0->0->0
        op1 = getLinkedList(9, 9, 9);
        op2 = getLinkedList(1);
        expectedSum = getLinkedList(1, 0, 0, 0);

        sum = sum(op1, op2);
        System.out.println(sum);
        assert (sum.equals(expectedSum));
    }
}

