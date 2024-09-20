package com.minesweeper.minesweeper.entity;

public class Cell {
    private boolean mine;
    private int number; // Number of adjacent mines
    private boolean revealed;
    private boolean flagged;
    private int x;
    private int y;

    public Cell(int x, int y) { // 생성자에 좌표 추가
        this.x = x;
        this.y = y;
        this.mine = false;
        this.revealed = false;
        this.flagged = false;
    }

    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.flagged = false;
    }

    public boolean isMine() {
        return mine;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
            return mine ? "💣" : number > 0 ? Integer.toString(number) : "";
        } else {
            return "";
        }
    }
}
