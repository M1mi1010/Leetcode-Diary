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
    public ListNode deleteMiddle(ListNode head) {
        ListNode slow = head;
        ListNode prev = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //Exception: one item
        if (fast == head) return null;

        //Exception: two items
        if (slow.next == null) {
            prev.next = null;
            return prev;
        }

        // At this point slow = middle item, slow.next is right item and prev is left
        prev.next = slow.next;

        return head;
    }
}
