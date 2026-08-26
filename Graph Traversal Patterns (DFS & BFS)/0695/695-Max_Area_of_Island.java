class Solution {
    int[][] dirs = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    if (area > max) max = area;
                }

            }
        }

        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        int area = 1;
        grid[i][j] = 2;
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >=0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                if (grid[ni][nj] == 1) {
                    area += dfs(grid, ni, nj);
                }
            }
        }
        return area;
    }
}
