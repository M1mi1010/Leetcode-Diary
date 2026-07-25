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
        //Edge case of one element
        ListNode dummy = new ListNode(-1000);
        ListNode lastNonDup = dummy;
        dummy.next = head;
        boolean duplicates = false;

        while (head != null && head.next != null) {

            while (head.next != null && head.val == head.next.val) {
                head = head.next;
                duplicates = true;
            }

            if (!duplicates) {
                lastNonDup = head;
                head = head.next;
            }
            else {
                head = head.next;
                lastNonDup.next = head;
            }
            duplicates = false;
        }
        return dummy.next;
    }
}
