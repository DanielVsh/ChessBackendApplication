package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getMoves() {
    Knight knight = new Knight(new Coordinates(FILE_B, RANK_1), Color.WHITE);

    assertEquals(8, knight.getMoves().size());
    assertTrue(knight.getMoves().containsAll(List.of(
        new CoordinatesShift(1, 2),
        new CoordinatesShift(-1, 2),
        new CoordinatesShift(1, -2),
        new CoordinatesShift(-1, -2),
        new CoordinatesShift(2, 1),
        new CoordinatesShift(-2, 1),
        new CoordinatesShift(2, -1),
        new CoordinatesShift(-2, -1))
      )
    );
  }

  @Test
  void getAvailableMoves() {
    Knight knight = new Knight(new Coordinates(FILE_G, RANK_6), BLACK);

    assertEquals(3, knight.getAvailableMoves(board).size());
    assertTrue(knight.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_F, RANK_4),
      new Coordinates(FILE_H, RANK_4))
    ));
  }

  @Test
  void getAttackMoves() {
    Knight knight = new Knight(new Coordinates(FILE_A, RANK_4), WHITE);

    assertEquals(8, knight.getAttackMoves().size());
    assertTrue(knight.getAttackMoves().containsAll(List.of(
      new CoordinatesShift(1, 2),
      new CoordinatesShift(-1, 2),
      new CoordinatesShift(1, -2),
      new CoordinatesShift(-1, -2),
      new CoordinatesShift(2, 1),
      new CoordinatesShift(-2, 1),
      new CoordinatesShift(2, -1),
      new CoordinatesShift(-2, -1))
    ));
  }

  @Test
  void getAvailableAttackMoves() {
    Knight knight = new Knight(new Coordinates(FILE_A, RANK_4), WHITE);
    board.setPiece(new Pawn(new Coordinates(FILE_C, RANK_5), BLACK), new Coordinates(FILE_C, RANK_5));
    board.setPiece(new Pawn(new Coordinates(FILE_B, RANK_6), WHITE), new Coordinates(FILE_B, RANK_6));

    assertEquals(4, knight.getAvailableAttackMoves(board).size());
    assertTrue(knight.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_B, RANK_2), new Coordinates(FILE_B, RANK_6),
      new Coordinates(FILE_C, RANK_3), new Coordinates(FILE_C, RANK_5)
    )));
  }
}