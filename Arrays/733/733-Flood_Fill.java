class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];

        if (original == color) {
            return image;
        }
        
        recurse(image, sr, sc, color, original);

        return image;
    }
    
    private void recurse(int[][] image, int sr, int sc, int color, int original) {
        int maxRows = image.length - 1;
        int maxCols = image[0].length - 1;

        //Color current
        image[sr][sc] = color;
        
        //Try to move right
        if ((sc != maxCols && image[sr][sc+1] != color) && image[sr][sc + 1] == original ) {
            image[sr][sc+1] = color;
            recurse(image, sr, sc + 1, color, original);
        }

        //Try to move down
        if ((sr != maxRows && image[sr+1][sc] != color) && image[sr+1][sc] == original) {
            image[sr+1][sc] = color;
            recurse(image, sr + 1, sc, color, original);
        }

        //Try to move left
        if ((sc != 0  && image[sr][sc-1] != color) && image[sr][sc - 1] == original) {
            image[sr][sc-1] = color;
            recurse(image, sr, sc - 1, color, original);
        }

        //Try to move up
        if ((sr != 0 && image[sr-1][sc] != color) && image[sr-1][sc] == original) {
            image[sr-1][sc] = color;
            recurse(image, sr - 1, sc, color, original);
        }
    }
}
