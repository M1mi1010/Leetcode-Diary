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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        StringBuilder sRoot = new StringBuilder();
        StringBuilder sSubRoot = new StringBuilder();

        preorder(root, sRoot);
        preorder(subRoot, sSubRoot);

        String s = sRoot.toString();
        String ss = sSubRoot.toString();

        return s.contains(ss);
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        //NLR
        if (root == null) {
            sb.append("#,");
            sb.append("null,");
            return;
        }

        sb.append(",").append(root.val).append(",");

        preorder(root.left, sb);
        preorder(root.right, sb);
    }
}
