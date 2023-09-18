package com.danielvishnievskyi.chesswebapp.controllers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import com.danielvishnievskyi.chesswebapp.services.chess.ChessInMemoryGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChessGameWebsocketController {
  private final SimpMessagingTemplate messagingTemplate;
  private final ChessInMemoryGameService chessInMemoryGameService;

  @MessageMapping("/game/{gameId}/move")
  public void makeMove(@DestinationVariable ChessGameMatchRequestDTO chessGameMatchRequestDTO, CoordinatesMove coordinatesMove) {
    ChessGame updatedGame = chessInMemoryGameService.makeMove(chessGameMatchRequestDTO, coordinatesMove);

    messagingTemplate.convertAndSend("/topic/game/" + chessGameMatchRequestDTO.getInMemoryId(), updatedGame);
  }
}
