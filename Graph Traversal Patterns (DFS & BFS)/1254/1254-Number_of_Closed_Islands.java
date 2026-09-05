class Solution {
    int[][] dirs = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };
    public int closedIsland(int[][] grid) {
        //Need to find every island of ones 

        //Eliminate all edge open islands
        for (int j = 0; j < grid[0].length; j++) { //top and bottom row
            if (grid[0][j] == 0) dfs(grid, 0, j);
            if (grid[grid.length - 1][j] == 0) dfs(grid, grid.length - 1, j);
        }
        
        for (int i = 0; i < grid.length; i++) { // left and right column
            if (grid[i][0] == 0) dfs(grid, i, 0);
            if (grid[i][grid[0].length - 1] == 0) dfs(grid, i, grid[0].length - 1);
        }

        //Need to traverse all islands
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {dfs(grid, i, j); count++;}
            }
        }

        return count;

    }

    public void dfs(int[][] grid, int i, int j) {
        grid[i][j] = 1;

        for (int[] dir : dirs) {
            int ni = dir[0] + i;
            int nj = dir[1] + j;

            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                if (grid[ni][nj] == 0) {
                    dfs(grid, ni, nj);
                }
            }
        }
    }

}
