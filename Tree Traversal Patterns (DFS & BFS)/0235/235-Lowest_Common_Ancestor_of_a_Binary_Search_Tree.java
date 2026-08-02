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
        return lca(root, p, q);
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        //Check if root is one of the targets
        //Check if we split the tree, if they both aren't null
        if (root.val < p.val && root.val < q.val) {
            return lca(root.right, p, q);
        }
        else if (root.val > p.val && root.val > q.val) {
            return lca(root.left, p, q);
        }
        else {
            return root;
        }
    }
}
