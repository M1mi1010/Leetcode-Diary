class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int mid;
        int row = 0;
        int col = 0;
        int noCols = matrix[0].length;
        int hi = noCols * matrix.length - 1;
        int lo = 0;
        
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;

            row = mid / noCols;
            col = mid % noCols;

            if (matrix[row][col] < target) lo = mid + 1;
            else if (matrix[row][col] > target) hi = mid - 1;
            else return true;
        }

        return false;
    }
}
