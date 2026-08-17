class Solution {
    int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        // mark visited
        grid[i][j] = '-';

        // explore 4 directions
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (0 <= ni && ni < grid.length && 0 <= nj && nj < grid[0].length) {
                if (grid[ni][nj] == '1') dfs(grid, ni, nj);
            }
        }
    }
}
