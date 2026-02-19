package com.example.oserogame.model;

public enum Player {
    BLACK("●"),
    WHITE("○"),
    EMPTY(" ");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
