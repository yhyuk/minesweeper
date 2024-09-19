package com.minesweeper.minesweeper.dto;

public record CellDto(
        int row,      // 셀의 행 번호
        int col,      // 셀의 열 번호
        boolean isMine,    // 지뢰 여부
        boolean isRevealed, // 셀이 공개되었는지 여부
        int adjacentMines  // 인접한 지뢰의 개수

) {}
