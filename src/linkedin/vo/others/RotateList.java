package linkedin.vo.others;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/rotate-list/
 *
 we need to know the length of linked list.After that, move the list after the (l-n%l )th node to the front to finish the rotation.

 Ex: {1,2,3} k=2 Move the list after the 1st node to the front

 Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.

 **/
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;

        ListNode fast =head, slow = head;

        int size = 1;
        while(fast.next != null) {
            size++;
            fast = fast.next;
        }

        for(int i=size - k%size; i>1; i--){
            slow = slow.next;
        }

        fast.next = head;
        head = slow.next;
        slow.next = null;

        return head;

    }
}
