package com.minesweeper.minesweeper.controller;

import com.minesweeper.minesweeper.entity.Board;
import com.minesweeper.minesweeper.entity.Cell;
import com.minesweeper.minesweeper.service.MinesweeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MinesweeperController {

    @Autowired
    private MinesweeperService minesweeperService;



    @GetMapping("/minesweeper")
    public String showGameBoard(Model model, @RequestParam(defaultValue = "beginner") String level) {
        Board board = minesweeperService.initializeBoard(level);
        model.addAttribute("board", board.getBoard());
        model.addAttribute("remainingMines", minesweeperService.getRemainingMines()); // 남은 지뢰 수 추가
        return "minesweeper";
    }

    @GetMapping("/reveal")
    @ResponseBody
    public Cell[][] revealCell(@RequestParam int x, @RequestParam int y) {
        return minesweeperService.revealCell(x, y);
    }

    @GetMapping("/flag")
    @ResponseBody
    public Cell[][] flagCell(@RequestParam int x, @RequestParam int y) {
        return minesweeperService.toggleFlag(x, y);
    }

    @GetMapping("/remainingMines")
    public ResponseEntity<Integer> getRemainingMines() {
        int remainingMines = minesweeperService.getRemainingMines();
        return ResponseEntity.ok(remainingMines);
    }


}
