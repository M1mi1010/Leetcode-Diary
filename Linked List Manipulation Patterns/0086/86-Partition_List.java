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
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode d1 = new ListNode(-101);
        ListNode d2 = new ListNode(-101);

        d1.next = head;
        d2.next = head;

        ListNode last = d1;
        ListNode start = d1;
        ListNode headD2 = d2;

        while (head != null) {
            ListNode next = head.next;
            if (head.val < x) {
                d1.next = head;
                d1 = d1.next;
                last = d1;
            }
            else {
                d2.next = head;
                d2 = d2.next;
            }

            head.next = null;
            head = next;
        }

        d2.next = null;
        last.next = headD2.next;
        return start.next;
    }
}
