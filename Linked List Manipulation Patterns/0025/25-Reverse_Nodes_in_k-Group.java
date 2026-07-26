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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode startGroup = head;
        ListNode endGroup = head;
        ListNode beforeGroup = dummy;
        ListNode afterGroup = null;

        ListNode lastValidNode = null;
        boolean enoughLeft = true;
        //Find beginning and end of the group

        while (startGroup != null) {
            for (int i = 0; i < k - 1; i++) {
                lastValidNode = endGroup; //Incase the end of the group is reached
                endGroup = endGroup.next;
                if (endGroup == null) {endGroup = lastValidNode; enoughLeft = false;}
            }

            if (!enoughLeft) break;

            afterGroup = endGroup.next;

            //Reverse between start of group and end 
            ListNode current = startGroup;
            ListNode prev = afterGroup;
            while (current != afterGroup) {
                ListNode n = current.next;

                current.next = prev;
                prev = current;
                current = n;
            }

            //Then update pointers
            // Current is at after the group
            if (beforeGroup != null) beforeGroup.next = prev;

            beforeGroup = startGroup;
            startGroup = current;
            endGroup = current;
            enoughLeft = true;
        }
        
        return dummy.next;
    }
}
