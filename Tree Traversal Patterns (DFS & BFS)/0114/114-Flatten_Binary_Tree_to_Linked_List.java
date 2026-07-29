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
    //redo making a linked list of nodes using preorder and then go from root.right continuously
    public void flatten(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(list, root);

        int count = 1;
        while (count < list.size()) {
            root.right = new TreeNode(list.get(count));
            root.left = null;
            root = root.right;
            count++;
        }
        
    }

    private void preorder(List<Integer> list, TreeNode root) {
        if (root == null) return;

        list.add(root.val);

        if (root.left == null && root.right == null) return;

        TreeNode right = root.right;
        TreeNode left = root.left;
        
        if (root.left == null) {
            preorder(list, root.right);
            return;
        }

        if (root.right == null) {
            preorder(list, root.left);
            return;
        }

        preorder(list, root.left);
        preorder(list, root.right);
    }
}
