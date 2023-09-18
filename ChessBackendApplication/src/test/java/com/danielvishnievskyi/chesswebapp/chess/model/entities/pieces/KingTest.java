package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getMoves() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    assertEquals(8, king.getMoves().size());
    assertTrue(king.getMoves().containsAll(List.of(
      new CoordinatesShift(-1, 1),
      new CoordinatesShift(0, 1),
      new CoordinatesShift(1, 1),
      new CoordinatesShift(-1, 0),
      new CoordinatesShift(1, 0),
      new CoordinatesShift(-1, -1),
      new CoordinatesShift(-1, 0),
      new CoordinatesShift(1, -1)
    )));
  }

  @Test
  void getAvailableMoves_AllMovesWithoutBarriers() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    assertEquals(8, king.getAvailableMoves(board).size());
    assertTrue(king.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, BoardRank.RANK_3),
      new Coordinates(FILE_C, BoardRank.RANK_4),
      new Coordinates(FILE_C, BoardRank.RANK_5),
      new Coordinates(FILE_D, BoardRank.RANK_3),
      new Coordinates(FILE_D, BoardRank.RANK_5),
      new Coordinates(FILE_E, BoardRank.RANK_3),
      new Coordinates(FILE_E, BoardRank.RANK_4),
      new Coordinates(FILE_E, BoardRank.RANK_5)
    )));
  }

  @Test
  void getAvailableMoves_WithBarriers() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    board.setPiece(new Pawn(new Coordinates(FILE_C, RANK_5), BLACK), new Coordinates(FILE_D, RANK_6));
    board.setPiece(new Rook(new Coordinates(FILE_B, RANK_6), BLACK), new Coordinates(FILE_H, RANK_3));

    assertEquals(3, king.getAvailableMoves(board).size());
    assertTrue(king.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, RANK_4),
      new Coordinates(FILE_D, RANK_5),
      new Coordinates(FILE_E, RANK_4)
    )));
  }

  @Test
  void getAvailableMoves_OnePieceDefendedByAnotherOne() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    board.setPiece(new Pawn(new Coordinates(FILE_E, RANK_4), BLACK), new Coordinates(FILE_E, RANK_4));
    board.setPiece(new Rook(new Coordinates(FILE_B, RANK_6), BLACK), new Coordinates(FILE_H, RANK_4));

    assertEquals(6, king.getAvailableMoves(board).size());
    assertTrue(king.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, RANK_3),
      new Coordinates(FILE_C, RANK_4),
      new Coordinates(FILE_C, RANK_5),
      new Coordinates(FILE_D, RANK_5),
      new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_E, RANK_3)
    )));
  }

  @Test
  void getAvailableAttackMoves_AllMovesWithoutBarriers() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    assertEquals(8, king.getAvailableAttackMoves(board).size());
    assertTrue(king.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, BoardRank.RANK_3),
      new Coordinates(FILE_C, BoardRank.RANK_4),
      new Coordinates(FILE_C, BoardRank.RANK_5),
      new Coordinates(FILE_D, BoardRank.RANK_3),
      new Coordinates(FILE_D, BoardRank.RANK_5),
      new Coordinates(FILE_E, BoardRank.RANK_3),
      new Coordinates(FILE_E, BoardRank.RANK_4),
      new Coordinates(FILE_E, BoardRank.RANK_5)
    )));
  }

  @Test
  void getAvailableAttackMoves_WithBarriers() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    board.setPiece(new Pawn(new Coordinates(FILE_C, RANK_5), BLACK), new Coordinates(FILE_D, RANK_6));
    board.setPiece(new Rook(new Coordinates(FILE_B, RANK_6), BLACK), new Coordinates(FILE_H, RANK_3));

    assertEquals(8, king.getAvailableAttackMoves(board).size());
    assertTrue(king.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, BoardRank.RANK_3),
      new Coordinates(FILE_C, BoardRank.RANK_4),
      new Coordinates(FILE_C, BoardRank.RANK_5),
      new Coordinates(FILE_D, BoardRank.RANK_3),
      new Coordinates(FILE_D, BoardRank.RANK_5),
      new Coordinates(FILE_E, BoardRank.RANK_3),
      new Coordinates(FILE_E, BoardRank.RANK_4),
      new Coordinates(FILE_E, BoardRank.RANK_5)
    )));
  }

  @Test
  void getAvailableAttackMoves_OnePieceDefendedByAnotherOne() {
    King king = new King(new Coordinates(FILE_D, BoardRank.RANK_4), Color.WHITE);

    board.setPiece(king, new Coordinates(FILE_D, BoardRank.RANK_4));

    board.setPiece(new Pawn(new Coordinates(FILE_E, RANK_4), BLACK), new Coordinates(FILE_E, RANK_4));
    board.setPiece(new Rook(new Coordinates(FILE_B, RANK_6), BLACK), new Coordinates(FILE_H, RANK_4));

    assertEquals(8, king.getAvailableAttackMoves(board).size());
    assertTrue(king.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, BoardRank.RANK_3),
      new Coordinates(FILE_C, BoardRank.RANK_4),
      new Coordinates(FILE_C, BoardRank.RANK_5),
      new Coordinates(FILE_D, BoardRank.RANK_3),
      new Coordinates(FILE_D, BoardRank.RANK_5),
      new Coordinates(FILE_E, BoardRank.RANK_3),
      new Coordinates(FILE_E, BoardRank.RANK_4),
      new Coordinates(FILE_E, BoardRank.RANK_5)
    )));
  }

}