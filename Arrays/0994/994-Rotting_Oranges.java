class Solution {
    int[][] dirs = {
        {1,0},
        {-1,0},
        {0,1},
        {0,-1}
    };

    public int orangesRotting(int[][] grid) {
        int minutes = 0;
        int noFresh = 0;
        int levels = 0  ;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) noFresh++;
                if (grid[i][j] == 2) q.offer(new int[]{i, j});
            }
        }

        while (!q.isEmpty() && noFresh > 0) {

            int size = q.size();

            // process exactly `size` rotten oranges
            for (int i = 0; i < size; i++) {
                int[] item = q.poll();

                for (int[] dir : dirs) {
                    int ni = dir[0] + item[0];
                    int nj = dir[1] + item[1];

                    if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                        if (grid[ni][nj] == 1) {
                            grid[ni][nj] = 2;
                            noFresh--;
                            q.offer(new int[]{ni, nj});
                        }
                    }
                }
            }

            levels++;
        }
        return noFresh == 0 ? levels : -1;
    }
}
