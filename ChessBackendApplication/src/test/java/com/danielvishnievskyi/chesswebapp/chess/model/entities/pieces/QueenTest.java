package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getAvailableMoves() {
    Queen queen = new Queen(new Coordinates(FILE_D, RANK_4), WHITE);

    assertEquals(19, queen.getAvailableMoves(board).size());
    assertTrue(queen.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_A, RANK_4), new Coordinates(FILE_A, RANK_7),
      new Coordinates(FILE_B, RANK_4), new Coordinates(FILE_B, RANK_6),
      new Coordinates(FILE_C, RANK_3), new Coordinates(FILE_C, RANK_4), new Coordinates(FILE_C, RANK_5),
      new Coordinates(FILE_D, RANK_3), new Coordinates(FILE_D, RANK_5), new Coordinates(FILE_D, RANK_6), new Coordinates(FILE_D, RANK_7),
      new Coordinates(FILE_E, RANK_3), new Coordinates(FILE_E, RANK_4), new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_F, RANK_4), new Coordinates(FILE_F, RANK_6),
      new Coordinates(FILE_G, RANK_4), new Coordinates(FILE_G, RANK_7),
      new Coordinates(FILE_H, RANK_4)
    )));
  }

  @Test
  void getAvailableAttackMoves() {
    Queen queen = new Queen(new Coordinates(FILE_D, RANK_4), WHITE);

    board.setPiece(new Pawn(new Coordinates(FILE_B, RANK_4), WHITE), new Coordinates(FILE_B, RANK_4));
    board.setPiece(new Pawn(new Coordinates(FILE_F, RANK_4), BLACK), new Coordinates(FILE_F, RANK_4));
    board.setPiece(new Pawn(new Coordinates(FILE_F, RANK_4), WHITE), new Coordinates(FILE_D, RANK_5));

    assertEquals(17, queen.getAvailableAttackMoves(board).size());
    assertTrue(queen.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_A, RANK_7),
      new Coordinates(FILE_B, RANK_2), new Coordinates(FILE_B, RANK_4), new Coordinates(FILE_B, RANK_6),
      new Coordinates(FILE_C, RANK_3), new Coordinates(FILE_C, RANK_4), new Coordinates(FILE_C, RANK_5),
      new Coordinates(FILE_D, RANK_2), new Coordinates(FILE_D, RANK_3), new Coordinates(FILE_D, RANK_5),
      new Coordinates(FILE_E, RANK_3), new Coordinates(FILE_E, RANK_4), new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_F, RANK_2), new Coordinates(FILE_F, RANK_4), new Coordinates(FILE_F, RANK_6),
      new Coordinates(FILE_G, RANK_7)
    )));
  }


  @Test
  void getAvailableMoves_KingAttackedByRookButQueenCanDefend() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K1r2Q/8/8/8 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Piece queen = board1.getPiece(new Coordinates(FILE_H, RANK_4)).get();

    assertEquals(1, queen.getAvailableMoves(board1).size());
    assertTrue(queen.getAvailableMoves(board1).contains(new Coordinates(FILE_E, RANK_4)));
  }


  @Test
  void getAvailableMoves_QueenPinnedToKingAndKingAttackedByKnight() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K1Q2r/8/3n4/8 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Piece queen = board1.getPiece(new Coordinates(FILE_E, RANK_4)).get();

    assertTrue(queen.getAvailableMoves(board1).isEmpty());
  }
}