package com.danielvishnievskyi.chesswebapp.services.chess;

import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ChessInMemoryGameServiceImplTest {

  private final ChessInMemoryGameServiceImpl chessInMemoryGameService = new ChessInMemoryGameServiceImpl();
  private ChessGameMatchRequestDTO gameMatchRequestDTO;

  private final UUID whitePlayerUUID = UUID.randomUUID();
  private final UUID blackPlayerUUID = UUID.randomUUID();

  @BeforeEach
  void init() {
    gameMatchRequestDTO = new ChessGameMatchRequestDTO(whitePlayerUUID, blackPlayerUUID);
  }


  @Test
  void newGame() {
    assertEquals(0, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    assertEquals(1, ChessInMemoryGameServiceImpl.getActiveGames().size());
  }

  @Test
  void getGame() {
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    assertEquals(1, ChessInMemoryGameServiceImpl.getActiveGames().size());
  }

  @Test
  void makeMove() {
  }

  @Test
  void removeGame() {
  }
}