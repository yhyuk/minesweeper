package com.minesweeper.minesweeper.service;

import com.minesweeper.minesweeper.dto.Board;
import com.minesweeper.minesweeper.dto.Cell;
import org.springframework.stereotype.Service;

@Service
public class MinesweeperService {

    private int remainingMines;
    private Board board;

    public Board initializeBoard(String level) {
        int width, height, mineCount;

        switch (level) {
            case "intermediate":
                width = 16;
                height = 16;
                mineCount = 40;
                break;
            case "advanced":
                width = 30;
                height = 16;
                mineCount = 99;
                break;
            default: // beginner
                width = 9;
                height = 9;
                mineCount = 10;
                break;
        }

        this.board = new Board(width, height, mineCount);
        this.remainingMines = board.mineCount();
        return this.board;
    }

    public Cell[][] revealCell(int x, int y) {
        if (board == null) {
            throw new IllegalStateException("지뢰찾기 초기화 실패");
        }
        board.reveal(x, y);

        return board.board();  // 이 부분에서 board 전체가 아닌 Cell 배열을 반환
    }

    public Cell[][] toggleFlag(int x, int y) {
        if (board == null) {
            throw new IllegalStateException("지뢰찾기 초기화 실패");
        }
        Cell cell = board.getCell(x, y);
        if (!cell.isRevealed()) {
            boolean wasFlagged = cell.isFlagged();
            cell.setFlagged(!wasFlagged); // 깃발 상태를 토글

            if (cell.isMine()) {
                if (!wasFlagged && cell.isFlagged()) {
                    decrementRemainingMines(); // 올바른 위치에 깃발을 꽂았을 때 지뢰 개수 감소
                } else if (wasFlagged && !cell.isFlagged()) {
                    incrementRemainingMines(); // 깃발을 다시 제거하면 지뢰 개수 증가
                }
            }
        }

        return board.board();
    }

    private void decrementRemainingMines() {
        remainingMines--;
    }

    private void incrementRemainingMines() {
        remainingMines++;
    }

    public int getRemainingMines() {
        return remainingMines;
    }


}
