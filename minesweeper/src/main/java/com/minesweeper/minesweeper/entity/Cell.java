package com.minesweeper.minesweeper.dto;

public class Cell {
    private boolean mine;
    private int number;
    private boolean revealed;
    private boolean flagged;

    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.flagged = false;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public String getDisplay() {
        if (flagged) {
            return "🚩";
        } else if (revealed) {
            return mine ? "💣" : Integer.toString(number);
        } else {
            return "";
        }
    }
}

