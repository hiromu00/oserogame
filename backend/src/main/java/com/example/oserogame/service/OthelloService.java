package com.example.oserogame.service;

import com.example.oserogame.model.GameState;
import com.example.oserogame.model.Player;
import org.springframework.stereotype.Service;

@Service
public class OthelloService {
    private GameState gameState = new GameState();

    public GameState getGameState() {
        return gameState;
    }

    public void resetGame() {
        this.gameState = new GameState();
    }

    public boolean makeMove(int x, int y) {
        if (gameState.isGameOver() || !isValidMove(x, y, gameState.getCurrentPlayer())) {
            return false;
        }

        gameState.getBoard()[x][y] = gameState.getCurrentPlayer();
        flipPieces(x, y, gameState.getCurrentPlayer());

        switchPlayer();

        if (!hasValidMoves(gameState.getCurrentPlayer())) {
            switchPlayer(); // Pass
            if (!hasValidMoves(gameState.getCurrentPlayer())) {
                gameState.setGameOver(true);
                calculateWinner();
            }
        }
        return true;
    }

    private boolean isValidMove(int x, int y, Player player) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || gameState.getBoard()[x][y] != Player.EMPTY) {
            return false;
        }
        return canFlipInAnyDirection(x, y, player);
    }

    private boolean canFlipInAnyDirection(int x, int y, Player player) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            if (canFlipInDirection(x, y, dx[i], dy[i], player)) return true;
        }
        return false;
    }

    private boolean canFlipInDirection(int x, int y, int dx, int dy, Player player) {
        Player opponent = (player == Player.BLACK) ? Player.WHITE : Player.BLACK;
        int nx = x + dx;
        int ny = y + dy;
        boolean foundOpponent = false;

        while (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
            Player current = gameState.getBoard()[nx][ny];
            if (current == Player.EMPTY) return false;
            if (current == opponent) {
                foundOpponent = true;
            } else if (current == player) {
                return foundOpponent;
            }
            nx += dx;
            ny += dy;
        }
        return false;
    }

    private void flipPieces(int x, int y, Player player) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            if (canFlipInDirection(x, y, dx[i], dy[i], player)) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                Player opponent = (player == Player.BLACK) ? Player.WHITE : Player.BLACK;
                while (gameState.getBoard()[nx][ny] == opponent) {
                    gameState.getBoard()[nx][ny] = player;
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
    }

    private void switchPlayer() {
        gameState.setCurrentPlayer(gameState.getCurrentPlayer() == Player.BLACK ? Player.WHITE : Player.BLACK);
    }

    private boolean hasValidMoves(Player player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, player)) return true;
            }
        }
        return false;
    }

    private void calculateWinner() {
        int black = 0, white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameState.getBoard()[i][j] == Player.BLACK) black++;
                else if (gameState.getBoard()[i][j] == Player.WHITE) white++;
            }
        }
        if (black > white) gameState.setWinner("BLACK Wins (" + black + " vs " + white + ")");
        else if (white > black) gameState.setWinner("WHITE Wins (" + white + " vs " + black + ")");
        else gameState.setWinner("Draw");
    }
}
