/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = prev.next;
        ListNode n = current.next;

        while (n != null) {
            ListNode after = n.next;

            prev.next = n;
            n.next = current;
            current.next = after;

            prev = current;
            current = after;
            if (current == null) return dummy.next;
            n = current.next;
        }

        return dummy.next;

    }
}
