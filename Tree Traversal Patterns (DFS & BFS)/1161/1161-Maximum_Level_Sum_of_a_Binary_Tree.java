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
    public int maxLevelSum(TreeNode root) {
        if (root == null) return -1;

        long maxSum = Integer.MIN_VALUE;
        int maxLevel = 0;

        int curLevel = 1;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            long curSum = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                curSum += n.val;

                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                maxLevel = curLevel;
            }

            curLevel++;
        }

        return maxLevel;
    }
}
