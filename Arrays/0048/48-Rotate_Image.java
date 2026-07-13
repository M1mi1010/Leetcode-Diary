class Solution {
    public void rotate(int[][] matrix) {
        //Flip along y=x diagonal
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i >= j) continue;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Flip horizontally
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int temp = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix[0].length  - j - 1] = matrix[i][j];
                matrix[i][j] = temp;
                
            }
        }
    }
}
