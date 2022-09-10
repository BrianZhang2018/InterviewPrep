package hubspot.codingOnsite;

import category.model.ListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {
    public static void main(String[] args) {}

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null || l2 == null) {
                curr.next = (l2 == null ? l1 : l2);
                break;
            }
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}