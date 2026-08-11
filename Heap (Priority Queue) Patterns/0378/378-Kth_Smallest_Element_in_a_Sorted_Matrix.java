class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]
        );

        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{i, 0});
        }

        for (int count = 0; count < k; count++) {
            int[] pair = pq.poll();

            int i = pair[0];
            int j = pair[1];

            if (count == k - 1) {
                return matrix[i][j];
            }

            if (j + 1 < matrix[0].length) {
                pq.offer(new int[]{i, j + 1});
            }
        }
        
        return 0;
    }
}
