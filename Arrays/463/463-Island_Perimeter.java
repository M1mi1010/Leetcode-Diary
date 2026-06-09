class Solution {
    public int islandPerimeter(int[][] grid) {
        //Each square either adds 3, 2 or 0 to the perimeter
        //If square on edge then 
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    total+=4;
                    //Check up and left
                    if (j != 0 && grid[i][j-1] == 1) {
                        total-=2;
                    }
                    if (i != 0 && grid[i-1][j] == 1) {
                        total-=2;
                    }
                }
            }
        }
        return total;
    }
}
