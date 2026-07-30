/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> list = new ArrayList<>();
    Map<TreeNode, TreeNode> map = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParents(root, target);
        bfs(target, k);

        int size = q.size();
        for (int i = 0; i < size; i++) {
            list.add(q.poll().val);
        }
        return list;
    }
    
    private void findParents(TreeNode root, TreeNode target) {
        if (root == null) return;

        if (root.left != null) {
            map.put(root.left, root);
            findParents(root.left, target);
        }

        if (root.right != null) {
            map.put(root.right, root);
            findParents(root.right, target);
        }
    }

    private void bfs(TreeNode target, int k) {
        Set<TreeNode> visited = new HashSet<>();
        int level = 0;

        q.offer(target);
        visited.add(target);

        while (!q.isEmpty()) {
            if (level == k) break;

            int size = q.size(); 

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null && !visited.contains(node.left)) {
                    q.offer(node.left);
                    visited.add(node.left);
                }

                if (node.right != null && !visited.contains(node.right)) {
                    q.offer(node.right);
                    visited.add(node.right);
                }

                TreeNode parent = map.get(node);
                if (parent != null && !visited.contains(parent)) {
                    q.offer(parent);
                    visited.add(parent);
                }
            }

            level++;
        }
    }
}
