package com.danielvishnievskyi.chesswebapp.services.chess;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChessInMemoryGameServiceImplTest {

  private final ChessInMemoryGameServiceImpl chessInMemoryGameService = new ChessInMemoryGameServiceImpl();
  private ChessGameMatchRequestDTO gameMatchRequestDTO;

  private final UUID whitePlayerUUID = UUID.randomUUID();
  private final UUID blackPlayerUUID = UUID.randomUUID();

  @BeforeEach
  void init() {
    ChessInMemoryGameServiceImpl.getActiveGames().clear();
    gameMatchRequestDTO = new ChessGameMatchRequestDTO(whitePlayerUUID, blackPlayerUUID);
  }

  @Test
  void newGame() {
    assertEquals(0, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    assertEquals(1, ChessInMemoryGameServiceImpl.getActiveGames().size());
    assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0", ChessInMemoryGameServiceImpl.getActiveGames().get(gameMatchRequestDTO.getInMemoryId()).generateFEN());
    assertTrue(ChessInMemoryGameServiceImpl.getActiveGames().containsKey(gameMatchRequestDTO.getInMemoryId()));
  }

  @Test
  void getGame() {
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    assertEquals(new ChessGame().generateFEN(), chessInMemoryGameService.getGame(gameMatchRequestDTO).generateFEN());
  }

  @Test
  void getGame2() {
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    ChessGame chessGame = ChessInMemoryGameServiceImpl.getActiveGames().get(gameMatchRequestDTO.getInMemoryId());
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_2),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_4)
    ));

    assertEquals("rnbqkbnr/pppppppp/8/8/5P2/8/PPPPP1PP/RNBQKBNR b KQkq - 0 1", chessInMemoryGameService.getGame(gameMatchRequestDTO).generateFEN());
  }

  @Test
  void makeMove() {
    chessInMemoryGameService.newGame(gameMatchRequestDTO);

    chessInMemoryGameService.makeMove(gameMatchRequestDTO, new CoordinatesMove(
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_2),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_4)
    ));

    assertEquals("rnbqkbnr/pppppppp/8/8/5P2/8/PPPPP1PP/RNBQKBNR b KQkq - 0 1", chessInMemoryGameService.getGame(gameMatchRequestDTO).generateFEN());

  }

  @Test
  void removeGame_ThereIsOnlyOneGame() {
    assertEquals(0, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.newGame(gameMatchRequestDTO);
    assertEquals(1, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.removeGame(gameMatchRequestDTO);
    assertEquals(0, ChessInMemoryGameServiceImpl.getActiveGames().size());
  }

  @Test
  void removeGame_ThereAreManyGamesRemoveOne() {
    assertEquals(0, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.newGame(gameMatchRequestDTO);
    chessInMemoryGameService.newGame(new ChessGameMatchRequestDTO(UUID.randomUUID(), UUID.randomUUID()));
    chessInMemoryGameService.newGame(new ChessGameMatchRequestDTO(UUID.randomUUID(), UUID.randomUUID()));
    assertEquals(3, ChessInMemoryGameServiceImpl.getActiveGames().size());
    chessInMemoryGameService.removeGame(gameMatchRequestDTO);
    assertEquals(2, ChessInMemoryGameServiceImpl.getActiveGames().size());
    assertThrows(RuntimeException.class, () -> chessInMemoryGameService.getGame(gameMatchRequestDTO));
  }
}