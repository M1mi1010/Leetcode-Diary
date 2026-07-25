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
    public boolean isPalindrome(ListNode head) {
        //Find middle of list
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //One or Two items in list
        if (fast == head) {
            //Two items
            if (fast.next != null) {
                if (fast.val == fast.next.val) return true;
                else return false;
            }
            //One item
            else return true;
        }

        // Slow pointer is at midpoint n fast is at end/one before end
        slow = slow.next;

        ListNode prev = null;
        while (slow != null) {
            //Reverse from slow, and go in order for start            
            ListNode next = slow.next;

            slow.next = prev;
            prev = slow;
            slow = next;
        }

        //When exited, prev is head of reversed list, and slow is null
        while (prev != null) {
            if (prev.val != head.val) return false;
            prev = prev.next;
            head = head.next;
        }

        return true;
    }
}
