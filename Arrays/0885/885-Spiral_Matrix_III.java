class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        //No checking against the boundaries 
        int[][] coords = new int[rows*cols][2];
        int count = 1;

        int stepLength = 1;

        //Initial position
        coords[0] = new int[]{rStart, cStart};

        // Continue searching until out of all boundaries
        while (count < rows*cols) {
            //Right, column changing
            for (int i = 0; i < stepLength; i++) {
                cStart++;
                if (rStart >= 0 && rStart < rows &&cStart >= 0 && cStart < cols) {
                    coords[count++] = new int[]{rStart, cStart};
                }
            }

            //Down
            for (int i = 0; i < stepLength; i++) {
                rStart++;
                if (rStart >= 0 && rStart < rows &&cStart >= 0 && cStart < cols) {
                    coords[count++] = new int[]{rStart, cStart};
                }
            }
            
            stepLength++;

            //Left
            for (int i = 0; i < stepLength; i++) {
                cStart--;
                if (rStart >= 0 && rStart < rows &&cStart >= 0 && cStart < cols) {
                    coords[count++] = new int[]{rStart, cStart};
                }
            }
            

            //Up
            for (int i = 0; i < stepLength; i++) {
                rStart--;
                if (rStart >= 0 && rStart < rows &&cStart >= 0 && cStart < cols) {
                    coords[count++] = new int[]{rStart, cStart};
                }
            }
            stepLength++;
        }
        return coords;
    }
}
