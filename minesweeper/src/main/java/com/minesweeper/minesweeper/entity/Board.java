package com.minesweeper.minesweeper.entity;

import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private final int mineCount;
    private final Cell[][] board;
    private int remainingMines; // 남은 지뢰 개수

    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.board = new Cell[width][height];
        initializeBoard();
    }

    // 특정 위치의 셀을 반환하는 메서드 추가
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return board[x][y];
        } else {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
    }

    private void initializeBoard() {
        // Initialize cells with x and y coordinates
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = new Cell(x, y);  // 좌표값을 전달하여 셀 초기화
            }
        }

        // Place mines randomly
        placeMines();

        // Calculate numbers (adjacent mine counts)
        calculateAdjacentMines();
    }

    // 남은 지뢰 개수를 반환하는 메서드
    public int getRemainingMines() {
        return remainingMines;
    }

    // 남은 지뢰 개수를 줄이는 메서드
    public void decrementRemainingMines() {
        this.remainingMines--;
    }

    // 남은 지뢰 개수를 늘리는 메서드 (깃발을 제거할 때 사용)
    public void incrementRemainingMines() {
        this.remainingMines++;
    }

    private void placeMines() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < mineCount) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            if (!board[x][y].isMine()) {
                board[x][y].setMine(true);
                placedMines++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!board[x][y].isMine()) {
                    int adjacentMines = countAdjacentMines(x, y);
                    board[x][y].setNumber(adjacentMines);
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

    public void reveal(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height || board[x][y].isRevealed()) {
            return;
        }

        board[x][y].setRevealed(true);

        // If no adjacent mines, reveal adjacent cells recursively
        if (board[x][y].getNumber() == 0 && !board[x][y].isMine()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    reveal(x + i, y + j);
                }
            }
        }
    }

    public void toggleFlag(int x, int y) {
        board[x][y].setFlagged(!board[x][y].isFlagged());
    }

    public Cell[][] getBoard() {
        return board;
    }
}
