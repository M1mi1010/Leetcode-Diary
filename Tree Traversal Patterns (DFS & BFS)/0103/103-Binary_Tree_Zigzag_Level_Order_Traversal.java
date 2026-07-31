/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zz = new ArrayList<>();
        if (root == null) return zz;

        Deque<TreeNode> dq = new ArrayDeque<>();

        dq.offer(root);
        int level = 0;

        //Read from left to right
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> thisLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                // Odd
                if (level % 2 != 0 ) {
                    TreeNode cur = dq.pollLast();
                    thisLevel.add(cur.val); 
                    
                    //Right to left processing
                    if (cur.right != null) {dq.addFirst(cur.right);}
                    if (cur.left != null) {dq.addFirst(cur.left);}
                }

                // Even
                else {
                    TreeNode cur = dq.pollFirst();
                    thisLevel.add(cur.val); 

                    //Left to right processing
                    if (cur.left != null) {dq.addLast(cur.left);}
                    if (cur.right != null) {dq.addLast(cur.right);}
                }
            }

            zz.add(thisLevel);
            level++;

        }

        return zz;
    }
}
