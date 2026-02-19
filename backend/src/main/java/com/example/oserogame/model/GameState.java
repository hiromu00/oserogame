package com.example.oserogame.model;

import java.util.Arrays;

public class GameState {
    private Player[][] board;
    private Player currentPlayer;
    private boolean gameOver;
    private String winner;

    public GameState() {
        this.board = new Player[8][8];
        for (Player[] row : board) {
            Arrays.fill(row, Player.EMPTY);
        }
        // Initial setup
        board[3][3] = Player.WHITE;
        board[3][4] = Player.BLACK;
        board[4][3] = Player.BLACK;
        board[4][4] = Player.WHITE;

        this.currentPlayer = Player.BLACK;
        this.gameOver = false;
        this.winner = null;
    }

    // Getters and Seters
    public Player[][] getBoard() { return board; }
    public void setBoard(Player[][] board) { this.board = board; }
    public Player getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
}
