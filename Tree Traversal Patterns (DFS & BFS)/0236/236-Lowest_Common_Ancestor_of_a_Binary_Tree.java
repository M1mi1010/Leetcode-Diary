/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p == root || q == root) return root;

        return postorder(root, p, q);
    }

    private TreeNode postorder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        TreeNode left = postorder(root.left, p, q);
        TreeNode right = postorder(root.right, p, q);

        if (root == p || root == q) return root;

        if (left != null && right != null) return root;

        //Return whichever side isnt null
        return left != null ? left : right;
    }
}
