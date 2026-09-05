class Solution {
    List<int[]> surIslands = new ArrayList<>();
    List<int[]> restoreIslands = new ArrayList<>();
    int[][] dirs = {
        {0,1},
        {0, -1},
        {1,0}, 
        {-1, 0}
    };
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') bfs(board, i, j);
            }
        }

        //Mark all valid islands as X
        for (int i = 0; i < surIslands.size(); i++) {
            int[] coord = surIslands.get(i);
            board[coord[0]][coord[1]] = 'X';
        }

        //Mark all invalid ones as O
        for (int i = 0; i < restoreIslands.size(); i++) {
            int[] coord = restoreIslands.get(i);
            board[coord[0]][coord[1]] = 'O';
        }

    }

    //
    private void bfs(char[][] board, int i, int j) {
        //Keep a list of all the elements visited in the island, if its touching an edge, disregard the whole island
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{i, j});
        board[i][j] = 'S';
        boolean touchedEdge = false;
        List<int[]> island = new ArrayList<>();
        
        while (!q.isEmpty()) {    
            int size = q.size();

            for (int k = 0; k < size; k++) {
                int[] start = q.poll();
                if (start[0] == 0 || start[0] == board.length - 1 || start[1] == 0 || start[1] == board[0].length - 1) {
                    touchedEdge = true;
                }

                island.add(start);

                int ni;
                int nj;

                for (int[] dir : dirs) {
                    ni = dir[0] + start[0];
                    nj = dir[1] + start[1];

                    if (ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length) {
                        if (board[ni][nj] == 'O') {
                            q.offer(new int[]{ni, nj});
                            board[ni][nj] = 'S';
                        }
                        

                    }
                }
            }
        }
        if (!touchedEdge) surIslands.addAll(island);
        else restoreIslands.addAll(island);
    }
}
