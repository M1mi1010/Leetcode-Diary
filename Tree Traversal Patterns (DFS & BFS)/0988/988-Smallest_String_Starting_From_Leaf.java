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
    String smallest = "";   

    public String smallestFromLeaf(TreeNode root) {
        //Need to find all the paths from the root to the leaves
        findPaths(root, new StringBuilder());

        return smallest;
    }

    private void findPaths(TreeNode root, StringBuilder path) {
        if (root == null) return;

        path.append((char)(root.val + 97));

        if (root.left == null && root.right == null) {
            String current = new StringBuilder(path).reverse().toString();

            if (smallest.isEmpty() || current.compareTo(smallest) < 0) {
                smallest = current;
            }
        }
    
        if (root.left == null) {
            findPaths(root.right, path);
            return;
        }

        if (root.right == null) {
            findPaths(root.left, path);
            return;
        }

        StringBuilder leftPath = new StringBuilder(path);
        StringBuilder rightPath = new StringBuilder(path);

        findPaths(root.left, leftPath);
        findPaths(root.right, rightPath);
    }
 

}
