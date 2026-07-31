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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> maxx = new ArrayList<>();

        if (root == null) return maxx;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            int currentMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                currentMax = Math.max(currentMax, n.val);

                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            maxx.add(currentMax);
        }

        return maxx;
    }
}
