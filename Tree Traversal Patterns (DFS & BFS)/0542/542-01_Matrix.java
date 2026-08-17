class Solution {

    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> q = new LinkedList<>();
        int[][] m = new int[mat.length][mat[0].length];
        int[][] dirs = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                    m[i][j] = 0;
                }
                else {
                    m[i][j] = -1;
                }

            }
        }
        
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int item[] = q.poll();
                for (int[] dir : dirs) {
                    int ni = dir[0] + item[0];
                    int nj = dir[1] + item[1];

                    if (ni >= 0 && ni < mat.length && nj >= 0 && nj < mat[0].length) {
                        if (m[ni][nj] == -1) {
                            q.offer(new int[]{ni, nj});
                            m[ni][nj] = level;
                        }
                    }
                }
            }
            level++;
        }
        return m;
    }
}
