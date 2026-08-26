class Solution {
    int ogColor;
    int[][] dirs = {
        {0,1},
        {0,-1}, 
        {1,0},
        {-1,0}
    };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;

        ogColor = image[sr][sc];
        dfs(image, sr, sc, color);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color) {
        image[sr][sc] = color;

        for (int[] dir : dirs) {
            int ni = sr + dir[0];
            int nj = sc + dir[1];

            //if a valid index and the color is the original color
            if (ni >= 0 && ni < image.length && nj >= 0 && nj < image[0].length) {
                if (image[ni][nj] == ogColor) dfs(image, ni, nj, color);
            }
        } 
    }
}
