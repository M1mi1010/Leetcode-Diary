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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = head;
        ListNode ahead = head;
        while (head != null) {
            while (ahead.next != null && ahead.val == ahead.next.val) {
                ahead = ahead.next;
            }
            //Ahead skipped to last duplicate make head point to next non duplicate
            ahead = ahead.next;
            head.next = ahead;
            head = head.next;
        }
        return start;
    }
}
