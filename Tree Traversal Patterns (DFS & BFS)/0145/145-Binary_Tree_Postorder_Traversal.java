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
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return list;
    }

    private void postorder(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            postorder(root.left);
        }

        if (root.right != null) {
            postorder(root.right);
        }

        list.add(root.val);
    }
}
