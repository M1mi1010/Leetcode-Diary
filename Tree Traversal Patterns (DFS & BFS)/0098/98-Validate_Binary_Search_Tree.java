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
    Integer prev = null;
    boolean ordered = true;
    public boolean isValidBST(TreeNode root) {
        checkInorder(root);
        return ordered;
    }

    private void checkInorder(TreeNode root) {
        if (root == null) return;

        checkInorder(root.left);

        if (prev != null) {
            if (root. val <= prev) ordered = false;
        }
        prev = root.val;

        checkInorder(root.right);
    }


}
