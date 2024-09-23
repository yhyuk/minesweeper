package com.minesweeper.minesweeper.dto;

import java.util.Random;

public record Board(
        int width,
        int height,
        int mineCount,
        Cell[][] board,
        int remainingMines
) {
    public Board(int width, int height, int mineCount) {
        this(width, height, mineCount, new Cell[width][height], mineCount);
        initializeBoard();
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return board[x][y];
        } else {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
    }

    private void initializeBoard() {
        // 초기화
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = new Cell(x, y);  // 좌표값을 전달하여 셀 초기화
            }
        }

        // 지뢰 위치 설정
        placeMines();

        // 주위 지뢰 개수 계산
        calculateAdjacentMines();
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

        // 주위에 지뢰 없을 경우 재귀
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
}
