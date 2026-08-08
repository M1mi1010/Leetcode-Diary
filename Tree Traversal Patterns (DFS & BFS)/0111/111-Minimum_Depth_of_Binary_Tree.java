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
    public int minDepth(TreeNode root) {
        return postorder(root);
    }

    private int postorder(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = postorder(root.left);
        int rightHeight = postorder(root.right);
        int current;

        if (root.left == null) {
            current = rightHeight + 1;
        }
        else if (root.right == null) {
            current = leftHeight + 1;
        }
        else {
            current = leftHeight > rightHeight ? rightHeight + 1 : leftHeight + 1;
        }

        return current;
    }
}
