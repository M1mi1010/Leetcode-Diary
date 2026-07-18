class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int i = 0;
        int j = 0;
        int[] arr = new int[mat.length * mat[0].length];
        int index = 0;
        while (index < mat.length * mat[0].length) {
            //Go up, i decreasing
            while (i >= 0 && j < mat[0].length) {
                arr[index++] = mat[i][j];
                i--;
                j++;
            }

            // Went past right edge
            if (j == mat[0].length) {j = mat[0].length - 1; i+=2;}

            //Went over top
            else {i = 0;} //(i == -1)

            if (index == mat.length * mat[0].length) break;

            //Go down, j decreasing
            while (i < mat.length && j >= 0) {
                arr[index++] = mat[i][j];
                i++;
                j--;
            }

            //Went under
            if (i == mat.length) {i = mat.length - 1; j+=2;} 
            //Went past left edge
            else {j = 0;}
        }

        return arr;
    }
}
