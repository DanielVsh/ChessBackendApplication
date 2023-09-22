package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

  private Board board;

  @BeforeEach
  void init() {
    board = new Board();
  }

  @Test
  void getAvailableMoves_FromInitPosition_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_2), WHITE);

    assertEquals(2, pawn.getAvailableMoves(board).size());
    assertTrue(pawn.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, RANK_3),
      new Coordinates(FILE_C, RANK_4)
    )));
  }

  @Test
  void getAvailableMoves_AfterInitPosition_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_3), WHITE);

    assertEquals(1, pawn.getAvailableMoves(board).size());
    assertTrue(pawn.getAvailableMoves(board).contains(new Coordinates(FILE_C, RANK_4)));
  }

  @Test
  void getAvailableMoves_FromInitPosition_Black() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_7), BLACK);

    assertEquals(2, pawn.getAvailableMoves(board).size());
    assertTrue(pawn.getAvailableMoves(board).containsAll(List.of(
      new Coordinates(FILE_C, RANK_6),
      new Coordinates(FILE_C, RANK_5)
    )));
  }

  @Test
  void getAvailableMoves_AfterInitPosition_Black() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_6), BLACK);

    assertEquals(1, pawn.getAvailableMoves(board).size());
    assertTrue(pawn.getAvailableMoves(board).contains(new Coordinates(FILE_C, RANK_5)));
  }

  @Test
  void getAvailableMoves_FromInitPositionWithBarrier_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_2), WHITE);

    board.setPiece(new Knight(new Coordinates(FILE_A, RANK_7), WHITE), new Coordinates(FILE_C, RANK_3));

    assertEquals(0, pawn.getAvailableMoves(board).size());
  }

  @Test
  void getAvailableAttackMoves_FromInitPositionWithBarrierAndBlackPieceToTake_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_2), WHITE);

    board.setPiece(new Knight(new Coordinates(FILE_A, RANK_7), WHITE), new Coordinates(FILE_C, RANK_3));
    board.setPiece(new Knight(new Coordinates(FILE_A, RANK_7), BLACK), new Coordinates(FILE_B, RANK_3));

    assertEquals(2, pawn.getAvailableAttackMoves(board).size());
    assertTrue(pawn.getAvailableAttackMoves(board).containsAll(List.of(new Coordinates(FILE_B, RANK_3), new Coordinates(FILE_D, RANK_3))));
  }

  @Test
  void getAvailableMoves_FromInitPositionWithBarrierAndBlackPieceToTake_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_C, RANK_2), WHITE);

    board.setPiece(new Knight(new Coordinates(FILE_A, RANK_7), WHITE), new Coordinates(FILE_C, RANK_3));
    board.setPiece(new Knight(new Coordinates(FILE_A, RANK_7), BLACK), new Coordinates(FILE_B, RANK_3));

    assertEquals(1, pawn.getAvailableMoves(board).size());
    assertTrue(pawn.getAvailableMoves(board).contains(new Coordinates(FILE_B, RANK_3)));
  }

  @Test
  void getAvailableMoves_EnPassant_White() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/pppppppp/8/1P6/8/8/P1PPPPPP/RNBQKBNR b KQkq - 1 3");

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_7), new Coordinates(FILE_A, RANK_5)));

    Piece pawn = chessGame.getBoard().getPiece(new Coordinates(FILE_B, RANK_5)).get();

    assertEquals(2, pawn.getAvailableMoves(chessGame.getBoard()).size());
    assertTrue(pawn.getAvailableMoves(chessGame.getBoard()).containsAll(List.of(
      new Coordinates(FILE_A, RANK_6),
      new Coordinates(FILE_B, RANK_6)
    )));
  }

  @Test
  void getAvailableMoves_EnPassant_Black() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/p1pppppp/8/8/1p6/8/PPPPPPPP/RNBQKBNR w KQkq - 4 5");

    Piece pawn = chessGame.getBoard().getPiece(new Coordinates(FILE_B, RANK_4)).get();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_1), new Coordinates(FILE_F, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_7), new Coordinates(FILE_G, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_4)));

    assertEquals(2, pawn.getAvailableMoves(chessGame.getBoard()).size());
    assertTrue(pawn.getAvailableMoves(chessGame.getBoard()).containsAll(List.of(
      new Coordinates(FILE_A, RANK_3),
      new Coordinates(FILE_B, RANK_3)
    )));
  }

  @Test
  void getAvailableAttackMoves_White() {
    Pawn pawn = new Pawn(new Coordinates(FILE_B, RANK_2), WHITE);

    assertEquals(2, pawn.getAvailableAttackMoves(board).size());
    assertTrue(pawn.getAvailableAttackMoves(board).containsAll(List.of(
      new Coordinates(FILE_A, RANK_3),
      new Coordinates(FILE_C, RANK_3)
    )));
  }

  @Test
  void getAvailableMoves_PinnedToKingCanBeatAttacker() {
    ChessGame chessGame = new ChessGame("rnbqk1nr/pppppppp/5b2/4P3/3K4/8/PPPP1PPP/RNBQ1BNR w kq - 0 1");

    Board board1 = chessGame.getBoard();

    Pawn pawn = (Pawn) board1.getPiece(new Coordinates(FILE_E, RANK_5)).get();

    assertEquals(1, pawn.getAvailableMoves(board1).size());
    assertTrue(pawn.getAvailableMoves(board1).contains(new Coordinates(FILE_F, RANK_6)));
  }

  @Test
  void getAvailableMoves_PinnedToKing() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/1K2P2r/8/8/8 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Pawn pawn = (Pawn) board1.getPiece(new Coordinates(FILE_E, RANK_4)).get();

    assertTrue(pawn.getAvailableMoves(board1).isEmpty());
  }

  @Test
  void getAvailableMoves_PinnedToKingCanBeatAttackerButKindAttackedByAnotherPiece() {
    ChessGame chessGame = new ChessGame("7k/8/3b4/2P5/1K5r/8/8/8 w - - 0 1");

    Board board1 = chessGame.getBoard();

    Pawn pawn = (Pawn) board1.getPiece(new Coordinates(FILE_C, RANK_5)).get();

    System.out.println(pawn.getAvailableMoves(board1));
    assertTrue(pawn.getAvailableMoves(board1).isEmpty());
  }

}