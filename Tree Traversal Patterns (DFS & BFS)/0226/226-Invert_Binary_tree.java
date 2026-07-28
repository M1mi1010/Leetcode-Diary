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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invert(root);

        return root;
    }

    private void invert(TreeNode root) {
        if (root == null) return;
        if (root.left == null) {
            root.left = root.right;
            root.right = null;
            invert(root.left);
            return;
        }

        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            invert(root.right);
            return;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        invert(root.right);
        invert(root.left);
    }
}
