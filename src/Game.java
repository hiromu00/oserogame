public class Game {
    private String currentPlayer;
    private boolean gameOver;
    private Reverse reverse;
    
    public Game() {
        this.currentPlayer = Main.black;
        this.gameOver = false;
        this.reverse = new Reverse();
    }
    
    public boolean isValidMove(int x, int y) {
        // 範囲チェック
        if (x < 1 || x > 8 || y < 1 || y > 8) {
            return false;
        }
        
        // 空きマスチェック
        if (!Main.board[x][y].equals(Main.empty)) {
            return false;
        }
        
        // ひっくり返せる石があるかチェック
        return canFlipInAnyDirection(x, y, currentPlayer);
    }
    
    private boolean canFlipInAnyDirection(int x, int y, String player) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < 8; i++) {
            if (canFlipInDirection(x, y, dx[i], dy[i], player)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean canFlipInDirection(int x, int y, int dx, int dy, String player) {
        String opponent = player.equals(Main.black) ? Main.white : Main.black;
        int nx = x + dx;
        int ny = y + dy;
        boolean foundOpponent = false;
        
        while (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
            if (Main.board[nx][ny].equals(Main.empty)) {
                return false;
            }
            if (Main.board[nx][ny].equals(opponent)) {
                foundOpponent = true;
            } else if (Main.board[nx][ny].equals(player)) {
                return foundOpponent;
            }
            nx += dx;
            ny += dy;
        }
        return false;
    }
    
    public boolean makeMove(int x, int y) {
        if (!isValidMove(x, y)) {
            return false;
        }
        
        Main.board[x][y] = currentPlayer;
        flipPieces(x, y, currentPlayer);
        switchPlayer();
        
        // パスの処理
        if (!hasValidMoves()) {
            switchPlayer();
            if (!hasValidMoves()) {
                gameOver = true;
            }
        }
        
        return true;
    }
    
    private void flipPieces(int x, int y, String player) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < 8; i++) {
            if (canFlipInDirection(x, y, dx[i], dy[i], player)) {
                flipInDirection(x, y, dx[i], dy[i], player);
            }
        }
    }
    
    private void flipInDirection(int x, int y, int dx, int dy, String player) {
        String opponent = player.equals(Main.black) ? Main.white : Main.black;
        int nx = x + dx;
        int ny = y + dy;
        
        while (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8 && Main.board[nx][ny].equals(opponent)) {
            Main.board[nx][ny] = player;
            nx += dx;
            ny += dy;
        }
    }
    
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(Main.black) ? Main.white : Main.black;
    }
    
    private boolean hasValidMoves() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (isValidMove(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getWinner() {
        int blackCount = 0, whiteCount = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (Main.board[i][j].equals(Main.black)) blackCount++;
                else if (Main.board[i][j].equals(Main.white)) whiteCount++;
            }
        }
        
        if (blackCount > whiteCount) return Main.black + "の勝利";
        else if (whiteCount > blackCount) return Main.white + "の勝利";
        else return "引き分け";
    }
}