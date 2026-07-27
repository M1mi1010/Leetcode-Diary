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
    public void reorderList(ListNode head) {
        if (head == null) return;
        //Find midpoint element
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode midHead = slow;
        ListNode mid = slow;
        ListNode prev = slow.next;
        if (slow.next == null) {
            return;
        }
        slow.next = null; //Disconnect
        ListNode current = prev.next;
        prev.next = null; //Prevent previous node (beginning of second half) from continuing to point to next item

        // Build a second linked list from after the midpoint to the end backwards
        while (current != null) {
            ListNode next = current.next;
            current.next =  prev;
            prev = current;
            current = next;
        } //The head of the next list should be prev
        current = prev; 

        ListNode p1 = head;
        while (p1 != null && current != null) { // not at end of either /at midpoint
            ListNode next1 = p1.next;
            ListNode next2 = current.next;
            
            if (p1 == mid) break;

            p1.next = current;
            current.next = next1;

            p1 = next1;
            current = next2;
        }
        if (p1 == mid) p1.next = null;
        if (current == mid) current.next = null;
    }
}
