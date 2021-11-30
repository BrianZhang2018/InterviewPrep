package discovery;

import model.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11423/Short-but-recursive-Java-code-with-comments
 *
 * Discovery phone screening
 * Created by brianzhang on 9/27/20.
 */
public class ReverseInKGroup {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2; n2.next=n3; n3.next=n4;
        ListNode head = reverseKGroup(n1, 2);
        while(head != null) {System.out.println(head.val);head = head.next;}
    }

    // recursive solution
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = head;
        int count = 0;
        while (prev != null && count != k) { // find the k+1 node which is the head of reverse group
            prev = prev.next;
            count++;
        }
        // 解题思路: recursive reverse linkedList that means we do reversal started from the end of the linkedList
        // so, the prev the head-pointer to already reversed part in previous recursive
        if (count == k) { // if k+1 node is found
            prev = reverseKGroup(prev, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, need to be reversed
            // prev - head-pointer to already reversed part in last recursive function;
            while (count-- > 0) { // reverse current k-group (standard reverse linkedList way)
                ListNode tmp = head.next;
                head.next = prev;
                prev = head;
                head = tmp;
            }
            head = prev; // flow: return 4 (next->3), then the 1.next = 4 in next parent recursive call
        }
        return head;
    }


    // non-recursive
    public ListNode reverseKGroup1(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }
}
