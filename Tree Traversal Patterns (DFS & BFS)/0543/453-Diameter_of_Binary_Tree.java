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
    // Measuring height in nodes
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        postorder(root);
        return diameter;
    }

    private int postorder(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = postorder(root.left);
        int rightHeight = postorder(root.right);

        diameter = Math.max(leftHeight + rightHeight, diameter);
        // return current height
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
