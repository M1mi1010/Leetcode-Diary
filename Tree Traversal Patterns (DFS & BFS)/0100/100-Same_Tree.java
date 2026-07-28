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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSame(p, q);
    }

    private boolean checkSame(TreeNode p, TreeNode q) {
        //Check if all are null then end of tree reached
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        //Check if one is null
        if (p.val != q.val) return false;

        return checkSame(p.left, q.left) && checkSame(p.right, q.right);
    }
}
