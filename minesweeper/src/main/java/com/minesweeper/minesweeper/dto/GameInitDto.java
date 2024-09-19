package com.minesweeper.minesweeper.dto;

import java.util.List;

public record GameInitDto(
        int rows,         // 보드의 행 개수
        int cols,         // 보드의 열 개수
        int mines,        // 지뢰 개수
        List<CellDto> board,  // 셀 상태를 담은 리스트
        boolean gameOver
) {

}

