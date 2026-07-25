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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        
        ListNode current = head;

        // Traverse to find node items at left and right
        ListNode first = null;
        ListNode prev = null;

        for (int i = 0; i < right - 1; i++) {
            if (i == left - 2) {prev = current;}
            if (i == left - 1) {first = current;}
            current = current.next;
        }
        
        ListNode after = current.next;
        ListNode before = prev;
        ListNode tail = first;

        prev = after;

        while (first != null && first != after) {
            ListNode n = first.next;
            first.next = prev;
            prev = first;
            first = n;
        }
        
        tail.next = first;
        
        if (before != null) {
            before.next = prev;
        }
        else {
            head = prev;
        }

        
        return head;
    }
}
