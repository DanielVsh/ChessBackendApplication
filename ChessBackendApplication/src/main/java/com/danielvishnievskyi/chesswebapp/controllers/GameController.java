package com.danielvishnievskyi.chesswebapp.controllers;

import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import com.danielvishnievskyi.chesswebapp.services.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
  private final GameService gameService;

  @PostMapping("/create")
  public ResponseEntity<Void> createGameInMemory(@RequestBody ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    gameService.createGame(chessGameMatchRequestDTO);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/save")
  public ResponseEntity<Void> saveGame(@RequestBody ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    gameService.createGame(chessGameMatchRequestDTO);
    return ResponseEntity.ok().build();
  }
}
