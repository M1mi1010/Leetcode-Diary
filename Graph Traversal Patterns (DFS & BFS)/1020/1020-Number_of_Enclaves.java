class Solution {
    int[][] dirs = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };
    boolean touchesEdge = false;

    public int numEnclaves(int[][] grid) {
        int[][] seen = new int[grid.length][grid[0].length];
        int validCells = 0;
        int noLandCells = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && seen[i][j] == 0) {
                    touchesEdge = false;
                    int island = dfs(grid, i, j, seen);
                    noLandCells += island;
                    if (touchesEdge) validCells += island;
                }
            }
        }

        return noLandCells - validCells;
    }

    public int dfs(int[][] grid, int i, int j, int[][] seen) {
        int size = 0;
        //Mark starting position as seen
        seen[i][j] = 1;

        //Check if edge reached
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
            touchesEdge = true;
        }

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                if (seen[ni][nj] == 0 && grid[ni][nj] == 1 ) {
                    // Mark as seen
                    seen[ni][nj] = 1;
                    size += dfs(grid, ni, nj, seen);
                }
            }
        }

        return size + 1;
    }


}
