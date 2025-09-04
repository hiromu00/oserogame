public class Game {
    private static final String BLACK = "◯";
    private static final String WHITE = "●";
    private static final String EMPTY = " ";
    private static final int BOARD_SIZE = 8;
    
    private String[][] board;
    private String currentPlayer;
    private boolean gameOver;
    
    public Game() {
        this.board = new String[BOARD_SIZE][BOARD_SIZE];
        this.currentPlayer = BLACK;
        this.gameOver = false;
        initializeBoard();
    }
    
    private void initializeBoard() {
        // ボードを空で初期化
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        
        // 初期配置
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }
    
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        if (!board[row][col].equals(EMPTY)) {
            return false;
        }
        return hasValidDirection(row, col, currentPlayer);
    }
    
    private boolean hasValidDirection(int row, int col, String player) {
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                if (checkDirection(row, col, dr, dc, player)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkDirection(int row, int col, int dr, int dc, String player) {
        String opponent = player.equals(BLACK) ? WHITE : BLACK;
        int r = row + dr;
        int c = col + dc;
        boolean foundOpponent = false;
        
        while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE) {
            if (board[r][c].equals(EMPTY)) {
                return false;
            }
            if (board[r][c].equals(opponent)) {
                foundOpponent = true;
            } else if (board[r][c].equals(player)) {
                return foundOpponent;
            }
            r += dr;
            c += dc;
        }
        return false;
    }
    
    public boolean makeMove(int row, int col) {
        if (!isValidMove(row, col)) {
            return false;
        }
        
        board[row][col] = currentPlayer;
        flipPieces(row, col, currentPlayer);
        switchPlayer();
        
        if (!hasValidMoves()) {
            switchPlayer();
            if (!hasValidMoves()) {
                gameOver = true;
            }
        }
        
        return true;
    }
    
    private void flipPieces(int row, int col, String player) {
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                if (checkDirection(row, col, dr, dc, player)) {
                    flipInDirection(row, col, dr, dc, player);
                }
            }
        }
    }
    
    private void flipInDirection(int row, int col, int dr, int dc, String player) {
        String opponent = player.equals(BLACK) ? WHITE : BLACK;
        int r = row + dr;
        int c = col + dc;
        
        while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && board[r][c].equals(opponent)) {
            board[r][c] = player;
            r += dr;
            c += dc;
        }
    }
    
    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(BLACK) ? WHITE : BLACK;
    }
    
    private boolean hasValidMoves() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isValidMove(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void displayBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getWinner() {
        int blackCount = 0, whiteCount = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].equals(BLACK)) blackCount++;
                else if (board[i][j].equals(WHITE)) whiteCount++;
            }
        }
        
        if (blackCount > whiteCount) return BLACK;
        else if (whiteCount > blackCount) return WHITE;
        else return "引き分け";
    }
}