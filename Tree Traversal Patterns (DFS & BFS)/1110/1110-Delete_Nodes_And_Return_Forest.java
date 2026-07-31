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
    List<TreeNode> list = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (to_delete.length == 0) return list;

        Set<Integer> set = new HashSet<>();
        for (int num : to_delete) {
            set.add(num);
        }

        root = postorder(root, set);
        if (root != null) list.add(root);

        return list;
    }

    private TreeNode postorder(TreeNode root, Set<Integer> set) {
        if (root == null) return null;

        root.left = postorder(root.left, set); 
        root.right = postorder(root.right, set);

        if (set.contains(root.val)) {
            //Check if leaf
            if (root.left != null) list.add(root.left);
            if (root.right != null) list.add(root.right);

            root = null;
        }

        return root;
    }
}
