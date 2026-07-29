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

    //Left node right
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;

        inorder(root);
        return list;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            inorder(root.left);
        }

        list.add(root.val);
        
        if (root.right != null) {
            inorder(root.right);
        }

    }
}
