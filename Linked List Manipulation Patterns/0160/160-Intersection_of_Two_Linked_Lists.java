/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //while the end of the list hasnt been reached
        int aEndReached = 0;
        int bEndReached = 0;
        ListNode currentA = headA;
        ListNode currentB = headB;

        while (aEndReached != 2 || bEndReached != 2) {
            if (currentA == currentB) return currentA;
            
            if (currentA == null) {
                aEndReached++;
                currentA = headB;
                continue;
            }
            if (currentB == null) {
                bEndReached++;
                currentB = headA;
                continue;
            }

            currentA = currentA.next;
            currentB = currentB.next;
        }
        return null;
    }
}
