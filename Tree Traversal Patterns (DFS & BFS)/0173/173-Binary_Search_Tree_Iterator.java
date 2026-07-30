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
class BSTIterator {
    List<Integer> list = new ArrayList<>();
    int index;

    public BSTIterator(TreeNode root) {
        inorder(root);
        index = -1;
    }
    
    public int next() {
        index++;
        return list.get(index);
    }
    
    public boolean hasNext() {
        return index < list.size() - 1;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        list.add(root.val);

        inorder(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
