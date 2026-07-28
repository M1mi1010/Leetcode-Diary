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
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode oddHead = head;
        ListNode currentOdd = oddHead;
        ListNode evenHead = head.next;
        ListNode currentEven = evenHead;

        head = head.next.next;

        while (head != null) {
            currentOdd.next = head;
            currentOdd = currentOdd.next;
            head = head.next;

            if (head != null) {
                currentEven.next = head;
                currentEven = currentEven.next;
                head = head.next;
            }
        }

        currentOdd.next = evenHead;
        currentEven.next = null;

        return oddHead;
    }
}
