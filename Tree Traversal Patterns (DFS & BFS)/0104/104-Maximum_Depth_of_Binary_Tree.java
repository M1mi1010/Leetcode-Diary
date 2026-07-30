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
    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        postorder(root);
        return maxDepth;
    }

    private int postorder(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = postorder(root.left);
        int rightHeight = postorder(root.right);

        int current = Math.max(leftHeight, rightHeight) + 1;
        maxDepth = Math.max(current, maxDepth);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
