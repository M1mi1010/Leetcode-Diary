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
    int index = 1;
    int answer = -1;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        inorder(root, k);
        return answer;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null || answer != -1) {return;}

        inorder(root.left, k);

        if (answer != -1) return;
        
        if (index == k) {answer = root.val; return;}
        index++;

        inorder(root.right, k);

    }
}
