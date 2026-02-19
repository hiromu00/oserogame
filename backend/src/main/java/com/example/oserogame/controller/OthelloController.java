package com.example.oserogame.controller;

import com.example.oserogame.dto.MoveRequest;
import com.example.oserogame.model.GameState;
import com.example.oserogame.service.OthelloService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") // For development
public class OthelloController {

    private final OthelloService othelloService;

    public OthelloController(OthelloService othelloService) {
        this.othelloService = othelloService;
    }

    @GetMapping("/state")
    public GameState getGameState() {
        return othelloService.getGameState();
    }

    @PostMapping("/move")
    public GameState makeMove(@RequestBody MoveRequest request) {
        othelloService.makeMove(request.x(), request.y());
        return othelloService.getGameState();
    }

    @PostMapping("/reset")
    public GameState resetGame() {
        othelloService.resetGame();
        return othelloService.getGameState();
    }
}
