class Solution {
    public void gameOfLife(int[][] board) {
        //Read board storing of all of the board thatv need to be set to live or dead
        int[][] states = new int[board.length][board[0].length];
        int[][] directions = { //stores row change and column change
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},          {0, 1},
            {1,-1},  {1,0}, {1,1}
        };


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int noLive = 0;
                for (int[] d: directions) {
                    int row = i + d[0];
                    int col = j + d[1];
                    if (row >= 0 && row < board.length && col >=0 && col < board[0].length && board[row][col] == 1) {
                        noLive++;
                    }
                }

                //Now set the state, remember array is initialised to 0
                if (board[i][j] == 1) {
                    if (noLive == 3 || noLive == 2) {states[i][j] = 1;}
                }
                else {
                    if (noLive == 3) {states[i][j] = 1;}
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = states[i][j];
            }
        }
    }
}
