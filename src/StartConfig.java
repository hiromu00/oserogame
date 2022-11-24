public class StartConfig {
    public void boardInit() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Main.board[i][j] = Main.empty;
            }
        }
    }

    public void boardSetup(){
        for(int i=1; i<9; i++) {
            Main.board[0][i] = String.valueOf(i);
            Main.board[i][0] = String.valueOf(i);
        }
        Main.board[4][4] = Main.board[5][5] = Main.black;
        Main.board[5][4] = Main.board[4][5] = Main.white;
    }
}
