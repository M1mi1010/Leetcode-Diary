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
    int maxCount = 0;
    int currentCount = 0;
    Integer previous = null;
    List<Integer> modes = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        inorder(root);
        
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i); 
        }

        return result;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        //Left
        inorder(root.left);

        //Node
        if (previous == null || previous != root.val) {
            currentCount = 1;
        }
        else {
            currentCount++;
        }

        if (currentCount > maxCount) {
            maxCount = currentCount;
            modes.clear();
            modes.add(root.val);
        }
        else if (currentCount == maxCount) {
            modes.add(root.val);
        }

        previous = root.val;

        //Right
        inorder(root.right);

    }
}
