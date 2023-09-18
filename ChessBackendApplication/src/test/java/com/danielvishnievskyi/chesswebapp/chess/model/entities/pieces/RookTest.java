package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.RANK_4;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.RANK_5;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getAvailableMoves() {
    Rook rook = new Rook(new Coordinates(FILE_A, RANK_4), Color.WHITE);

    assertEquals(11, rook.getAvailableMoves(board).size());
    assertTrue(rook.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_A, BoardRank.RANK_3),
      new Coordinates(FILE_A, BoardRank.RANK_5),
      new Coordinates(FILE_A, BoardRank.RANK_6),
      new Coordinates(FILE_A, BoardRank.RANK_7),
      new Coordinates(FILE_B, RANK_4),
      new Coordinates(FILE_C, RANK_4),
      new Coordinates(FILE_D, RANK_4),
      new Coordinates(FILE_E, RANK_4),
      new Coordinates(BoardFile.FILE_F, RANK_4),
      new Coordinates(BoardFile.FILE_G, RANK_4),
      new Coordinates(BoardFile.FILE_H, RANK_4)
    )));
  }

  @Test
  void getAvailableAttackMoves() {
    Rook rook = new Rook(new Coordinates(FILE_A, RANK_4), Color.WHITE);

    board.setPiece(new Pawn(new Coordinates(FILE_A, RANK_5), WHITE), new Coordinates(FILE_A, RANK_5));
    board.setPiece(new Pawn(new Coordinates(FILE_D, RANK_4), BLACK), new Coordinates(FILE_D, RANK_4));

    Set<Coordinates> availableAttackMoves = rook.getAvailableAttackMoves(board);
    assertEquals(6, availableAttackMoves.size());
    assertTrue(availableAttackMoves.containsAll(List.of(
      new Coordinates(FILE_A, BoardRank.RANK_2),
      new Coordinates(FILE_A, BoardRank.RANK_3),
      new Coordinates(FILE_A, BoardRank.RANK_5),
      new Coordinates(FILE_B, BoardRank.RANK_4),
      new Coordinates(FILE_C, BoardRank.RANK_4),
      new Coordinates(FILE_D, BoardRank.RANK_4)
    )));
  }

}