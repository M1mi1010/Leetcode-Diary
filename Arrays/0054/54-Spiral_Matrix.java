class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int height = 0;
        int sideways = 0;
        int maxUp = 0;
        int maxDown = matrix.length - 1;
        int maxLeft = 0;
        int maxRight = matrix[0].length - 1;


        while (maxUp <= maxDown && maxLeft <= maxRight) {

            //Right
            while (sideways <= maxRight) {
                list.add(matrix[height][sideways]);
                sideways++;
            }
            sideways--;
            maxUp++;
            height++;

            if (maxUp > maxDown) break;

            //Down
            while (height <= maxDown) {
                list.add(matrix[height][sideways]);
                height++;
            }
            height--;
            maxRight--;
            sideways--;

            if (maxLeft > maxRight) break;

            //Left
            while (sideways >= maxLeft) {
                list.add(matrix[height][sideways]);
                sideways--;
            }
            sideways++;
            maxDown--;
            height--;

            if (maxUp > maxDown) break;

            //Up
            while (height >= maxUp) {
                list.add(matrix[height][sideways]);
                height--;
            }

            height++;
            maxLeft++;
            sideways++;
        }

        return list;
    }
}
