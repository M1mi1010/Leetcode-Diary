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
    public ListNode rotateRight(ListNode head, int k) {
        ListNode newHead = null;
        if (head == null) return null;

        //Find length of list   
        ListNode last = head;
        int count = 1;
        while (last.next != null) {
            count++;
            last = last.next;
        }

        k %= count;
        if (k == 0) return head;

        // Find the kth element from the end and new tail 
        ListNode newTail = head;
        for (int i = 0; i < count - k - 1; i++) {
            newTail = newTail.next;
        }
        newHead = newTail.next;

        last.next = head;
        newTail.next = null;
        
        return newHead;
    }
}
