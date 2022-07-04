package linkedin.vo.others;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/submissions/
 *
 * Created by brianzhang on 11/20/20.
 */
public class InsectionOfTwoLinkedList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;
        while(a!=b){
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
}