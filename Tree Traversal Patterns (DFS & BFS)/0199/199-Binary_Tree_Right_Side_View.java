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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        TreeNode last = null;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (i == size - 1) last = n;

                if (n.left != null) {q.offer(n.left);}
                if (n.right != null) {q.offer(n.right);}
            }

            list.add(last.val);
        }

        return list;
    }
}
