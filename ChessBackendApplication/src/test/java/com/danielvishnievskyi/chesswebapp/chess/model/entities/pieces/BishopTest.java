package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getAvailableMoves() {
    Bishop bishop = new Bishop(new Coordinates(FILE_D, RANK_4), WHITE);

    Set<Coordinates> availableMoves = bishop.getAvailableMoves(board);

    assertEquals(8, availableMoves.size());
    assertTrue(availableMoves.containsAll(List.of(
      new Coordinates(FILE_C, RANK_3),
      new Coordinates(FILE_E, RANK_3),
      new Coordinates(FILE_C, RANK_5),
      new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_B, RANK_6),
      new Coordinates(FILE_F, RANK_6),
      new Coordinates(FILE_A, RANK_7),
      new Coordinates(FILE_G, RANK_7)
    )));
  }

  @Test
  void getAvailableAttackMoves() {
    Bishop bishop = new Bishop(new Coordinates(FILE_C, RANK_3), WHITE);

    board.setPiece(new Pawn(new Coordinates(FILE_E, RANK_5), BLACK), new Coordinates(FILE_E, RANK_5));

    Set<Coordinates> availableAttackMoves = bishop.getAvailableAttackMoves(board);

    assertEquals(6, availableAttackMoves.size());
    assertTrue(availableAttackMoves.containsAll(List.of(
      new Coordinates(FILE_A, RANK_5),
      new Coordinates(FILE_B, RANK_2), new Coordinates(FILE_B, RANK_4),
      new Coordinates(FILE_D, RANK_2), new Coordinates(FILE_D, RANK_4),
      new Coordinates(FILE_E, RANK_5)
    )));
  }

  @Test
  void getAvailableMoves_BishopPinnedToKing() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K1B2r/8/8/8 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Piece bishop = board1.getPiece(new Coordinates(FILE_E, RANK_4)).get();

    assertTrue(bishop.getAvailableMoves(board1).isEmpty());
  }

  @Test
  void getAvailableMoves_BishopPinnedToKingCanBeatAttacker() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K5/8/4B3/5q2 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Piece bishop = board1.getPiece(new Coordinates(FILE_E, RANK_2)).get();

    assertEquals(2, bishop.getAvailableMoves(board1).size());
    assertTrue(bishop.getAvailableMoves(board1).containsAll(List.of(
      new Coordinates(FILE_D, RANK_3),
      new Coordinates(FILE_F, RANK_1)
    )));
  }

  @Test
  void getAvailableMoves_BishopPinnedToKingAndKingAttackedByKnight() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K5/8/3nB3/5q2 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Piece bishop = board1.getPiece(new Coordinates(FILE_E, RANK_2)).get();

    assertTrue(bishop.getAvailableMoves(board1).isEmpty());
  }
}