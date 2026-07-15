class Solution {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int start = 1;

        while (top <= bottom && right >= left) {

            //Right, columns changing
            for (int i = left; i <= right; i++) {
                m[top][i] = start;
                start++;
            }
            top++;

            //Down, row changing
            for (int i = top; i <= bottom; i++) {
                m[i][right] = start;
                start++;
            }
            right--;

            //Left, column changing
            if (top <= bottom) { //rows left
                for (int i = right; i >= left; i--) {
                    m[bottom][i] = start;
                    start++;
                }
                bottom--;
            }
            

            //Up, rows changing
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    m[i][left] = start;
                    start++;
                }
                left++;
            }
        }

        return m;
    }
}
