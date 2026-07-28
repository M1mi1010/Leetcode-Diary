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
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<String> result = new ArrayList<>();

        if (root.left == null && root.right == null) {
            result.add(String.valueOf(root.val));
            return result;
        }

        see(result, root, new StringBuilder());

        return result;
    }

    private void see(List<String> result, TreeNode node, StringBuilder path) {
        if (node == null) return;
        
        path.append(node.val);

        if (node.left == null && node.right == null) {
            result.add(path.toString());
            return;
        }

        path.append("->");

        if (node.right != null) {
            StringBuilder rightPath = new StringBuilder(path);
            see(result, node.right, rightPath);
        }

        if (node.left != null) {
            StringBuilder leftPath = new StringBuilder(path);
            see(result, node.left, leftPath);
        }
    }
}
