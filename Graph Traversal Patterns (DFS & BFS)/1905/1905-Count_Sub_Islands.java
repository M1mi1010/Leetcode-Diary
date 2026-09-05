class Solution {
    int[][] dirs = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };
    boolean inBoth = true;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1) {
                    inBoth = true;
                    dfs(grid2, i , j, grid1);
                    if (inBoth) count++; 
                }
            }
        }

        return count;
    }

    public void dfs(int[][] grid, int i, int j, int[][] grid2) {
        if (grid2[i][j] == 0) {
            inBoth = false;
            return;
        }

        grid[i][j] = 0;

        for (int[] dir : dirs) {
            int ni = dir[0] + i;
            int nj = dir[1] + j;

            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                if (grid[ni][nj] == 1) dfs(grid, ni, nj, grid2);
            }
        }
    }
}
