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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode current = head;
        boolean found = true;

        //Add everything to a priority queue, ascending order
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);

        for (ListNode l : lists) {
            if (l == null) continue;
            //Remove link to old list
            pq.offer(l);
        }

        while (!pq.isEmpty()) {
            ListNode n = pq.poll();
            ListNode next = new ListNode(n.val);
            current.next = next;
            current = current.next;
            if (n.next != null) {
                pq.offer(n.next);
            }
        }
        
        return head.next;
    }
}
