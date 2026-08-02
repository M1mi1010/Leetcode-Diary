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
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> list = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialise(root);
        return list;
    }

     
    private String serialise(TreeNode root) {
        if (root == null) {
            return "null";
        }

        String left = serialise(root.left);
        String right = serialise(root.right);

        String key = root.val + "," + left + "," + right;

        int count = map.getOrDefault(key, 0) + 1;
        map.put(key, count);

        if (count == 2) {
            list.add(root);
        }

        return key;
    }
}
