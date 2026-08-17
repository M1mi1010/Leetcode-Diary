class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        if (grid.length == 1) return 1;

        int[][] dirs = {
            {0,1},
            {1,1},
            {1,0},
            {-1,-1},
            {-1,0},
            {0, -1},
            {-1,1},
            {1, -1}
        };

        Queue<int[]> q = new LinkedList<>();
        int[] item = new int[]{0, 0, 1};
        q.offer(item);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                item = q.poll();

                for (int[] dir : dirs) {
                    int ni = dir[0] + item[0];
                    int nj = dir[1] + item[1];

                    if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                        if (grid[ni][nj] == 0) {
                            //Mark square as seen
                            grid[ni][nj] = 1;
                            if (ni == grid.length - 1 && nj == grid[0].length - 1) {
                                return item[2] + 1;
                            }
                            q.offer(new int[]{ni,nj, item[2] + 1});
                        }
                    }
                }
            }
        }

        return -1; 
    }
}
