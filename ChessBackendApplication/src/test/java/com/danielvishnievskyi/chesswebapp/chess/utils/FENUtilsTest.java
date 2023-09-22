package com.danielvishnievskyi.chesswebapp.chess.utils;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FENUtilsTest {

  @Test
  void testGenerateFEN() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_2),
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_4)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_7),
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_5)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_1),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_3)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_8),
      new Coordinates(BoardFile.FILE_H, BoardRank.RANK_4)
    ));

    assertEquals("rnb1kbnr/pppp1ppp/8/4p3/4P2q/5Q2/PPPP1PPP/RNB1KBNR w KQkq - 2 3", chessGame.generateFEN());
  }

  @Test
  void testGenerateFEN2() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_2),
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_4)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_7),
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_5)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_G, BoardRank.RANK_1),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_3)
    ));
    chessGame.movePiece(new CoordinatesMove(
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_7),
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_5)
    ));

    assertEquals("rnbqkbnr/ppp2ppp/8/3pp3/3P4/5N2/PPP1PPPP/RNBQKB1R w KQkq - 0 3", chessGame.generateFEN());
  }
}