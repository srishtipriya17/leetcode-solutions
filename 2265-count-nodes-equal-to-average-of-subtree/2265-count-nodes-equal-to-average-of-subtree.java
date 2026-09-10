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

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // Returns {sum, count}
    private int[] dfs(TreeNode node) {

        // If node is null
        if (node == null) {
            return new int[]{0, 0};
        }

        // Get sum and count from left subtree
        int[] left = dfs(node.left);

        // Get sum and count from right subtree
        int[] right = dfs(node.right);

        // Calculate total sum
        int sum = node.val + left[0] + right[0];

        // Calculate total number of nodes
        int count = 1 + left[1] + right[1];

        // Calculate average
        int average = sum / count;

        // Check whether node value equals average
        if (node.val == average) {
            ans++;
        }

        return new int[]{sum, count};
    }
}

