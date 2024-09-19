package com.minesweeper.minesweeper.dto;

import java.util.Random;

public class Board {
    private Cell[][] board;
    private int width;
    private int height;
    private int mineCount;

    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.board = new Cell[width][height];
        initializeBoard();
    }

    private void initializeBoard() {
        // 지뢰 랜덤 배치
        placeMines();
        // 주변 지뢰 개수 계산
        calculateNumbers();
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;

        while (minesPlaced < mineCount) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (!board[x][y].isMine()) {
                board[x][y].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateNumbers() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!board[x][y].isMine()) {
                    int count = countAdjacentMines(x, y);
                    board[x][y].setNumber(count);
                }
            }
        }
    }

    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx >= 0 && ny >= 0 && nx < width && ny < height && board[nx][ny].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell[][] getBoard() {
        return board;
    }
}

