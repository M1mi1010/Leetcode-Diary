class Solution {
    public int[][] transpose(int[][] matrix) {
        // If a square matrix
        // if (matrix.length == matrix[0].length) {
        int[][] matrix2 = new int[matrix[0].length][matrix.length]; 

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix2[j][i] = matrix[i][j];
            }
        }
        
        return matrix2;
    }
}
