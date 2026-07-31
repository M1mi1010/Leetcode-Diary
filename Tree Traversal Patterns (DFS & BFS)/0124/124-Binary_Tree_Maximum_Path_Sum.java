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
    int max = -1001;
    public int maxPathSum(TreeNode root) {
        int curr = postorder(root);
        return Math.max(max, curr);
    }

    private int postorder(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(postorder(root.left), 0);
        int right = Math.max(postorder(root.right), 0);

        max = Math.max(right + left + root.val, max);

        return root.val + Math.max(left, right);
    }
}
