package com.danielvishnievskyi.chesswebapp.chess.model.entities.board;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.*;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.utils.factories.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank.*;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  private Board board;

  @BeforeEach
  void initBoard() {
    board = new Board();
  }

  @Test
  void startPosition() {
    assertEquals(Rook.class, board.getPiece(new Coordinates(FILE_A, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_A, RANK_1)).get().getColor());
    assertEquals(Rook.class, board.getPiece(new Coordinates(FILE_A, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_A, RANK_8)).get().getColor());

    assertEquals(Knight.class, board.getPiece(new Coordinates(FILE_B, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_B, RANK_1)).get().getColor());
    assertEquals(Knight.class, board.getPiece(new Coordinates(FILE_B, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_B, RANK_8)).get().getColor());

    assertEquals(Bishop.class, board.getPiece(new Coordinates(FILE_C, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_C, RANK_1)).get().getColor());
    assertEquals(Bishop.class, board.getPiece(new Coordinates(FILE_C, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_C, RANK_8)).get().getColor());

    assertEquals(Queen.class, board.getPiece(new Coordinates(FILE_D, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_D, RANK_1)).get().getColor());
    assertEquals(Queen.class, board.getPiece(new Coordinates(FILE_D, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_D, RANK_8)).get().getColor());

    assertEquals(King.class, board.getPiece(new Coordinates(FILE_E, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_E, RANK_1)).get().getColor());
    assertEquals(King.class, board.getPiece(new Coordinates(FILE_E, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_E, RANK_8)).get().getColor());

    assertEquals(Bishop.class, board.getPiece(new Coordinates(FILE_F, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_F, RANK_1)).get().getColor());
    assertEquals(Bishop.class, board.getPiece(new Coordinates(FILE_F, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_F, RANK_8)).get().getColor());

    assertEquals(Knight.class, board.getPiece(new Coordinates(FILE_G, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_G, RANK_1)).get().getColor());
    assertEquals(Knight.class, board.getPiece(new Coordinates(FILE_G, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_G, RANK_8)).get().getColor());

    assertEquals(Rook.class, board.getPiece(new Coordinates(FILE_H, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_H, RANK_1)).get().getColor());
    assertEquals(Rook.class, board.getPiece(new Coordinates(FILE_H, RANK_8)).get().getClass());
    assertEquals(BLACK, board.getPiece(new Coordinates(FILE_H, RANK_8)).get().getColor());

    for (int i = 0; i <= 7; i++) {
      assertEquals(Pawn.class, board.getPiece(new Coordinates(BoardFile.fromNumber(i), RANK_2)).get().getClass());
      assertEquals(WHITE, board.getPiece(new Coordinates(BoardFile.fromNumber(i), RANK_2)).get().getColor());
      assertEquals(Pawn.class, board.getPiece(new Coordinates(BoardFile.fromNumber(i), RANK_7)).get().getClass());
      assertEquals(BLACK, board.getPiece(new Coordinates(BoardFile.fromNumber(i), RANK_7)).get().getColor());
    }

    for (int i = 2; i <= 5; i++) {
      for (int j = 0; j <= 7; j++) {
        assertEquals(Optional.empty(), board.getPiece(new Coordinates(BoardFile.fromNumber(j), BoardRank.fromNumber(i))));
      }
    }
  }

  @Test
  void getPiece() {
    assertEquals(King.class, board.getPiece(new Coordinates(FILE_E, RANK_1)).get().getClass());
    assertEquals(WHITE, board.getPiece(new Coordinates(FILE_E, RANK_1)).get().getColor());

    assertEquals(Optional.empty(), board.getPiece(new Coordinates(FILE_D, RANK_4)));
  }

  @Test
  void setPiece() {
    assertEquals(Optional.empty(), board.getPiece(new Coordinates(FILE_D, RANK_4)));

    board.setPiece(new Pawn(new Coordinates(FILE_D, RANK_3), BLACK), new Coordinates(FILE_D, RANK_4));

    assertEquals(Optional.empty(), board.getPiece(new Coordinates(FILE_D, RANK_3)));
    assertEquals(Pawn.class, board.getPiece(new Coordinates(FILE_D, RANK_4)).get().getClass());
  }

  @Test
  void removePiece() {
    assertEquals(Pawn.class, board.getPiece(new Coordinates(FILE_A, RANK_2)).get().getClass());

    board.removePiece(new Coordinates(FILE_A, RANK_2));

    assertEquals(Optional.empty(), board.getPiece(new Coordinates(FILE_A, RANK_2)));
  }

  @Test
  void getPiecesByColor() {
    List<Piece> piecesByColor = board.getPiecesByColor(WHITE);

    assertEquals(16, piecesByColor.size());
    piecesByColor.forEach(piece -> assertEquals(WHITE, piece.getColor()));

    board.removePiece(new Coordinates(FILE_A, RANK_1));

    board.removePiece(new Coordinates(FILE_A, RANK_8));
    board.removePiece(new Coordinates(FILE_A, RANK_7));

    assertEquals(15, board.getPiecesByColor(WHITE).size());
  }

  @Test
  void getAttackedSquaresByColor() {
    Board boardByFEN = BoardFactory.createBoardByFEN("rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1");
    List<Coordinates> coordinates = new ArrayList<>();
    coordinates.addAll(IntStream.rangeClosed(0, 7).mapToObj(i -> new Coordinates(BoardFile.fromNumber(i), RANK_3)).toList());
    coordinates.addAll(IntStream.rangeClosed(0, 7).mapToObj(i -> new Coordinates(BoardFile.fromNumber(i), RANK_2)).toList());
    coordinates.addAll(IntStream.rangeClosed(1, 6).mapToObj(i -> new Coordinates(BoardFile.fromNumber(i), RANK_1)).toList());
    coordinates.addAll(List.of(
      new Coordinates(FILE_D, RANK_4),
      new Coordinates(FILE_E, RANK_5),
      new Coordinates(FILE_G, RANK_5),
      new Coordinates(FILE_H, RANK_4)
    ));

    Set<Coordinates> attackedSquaresByColor = boardByFEN.getAttackedSquaresByColor(WHITE);
    assertEquals(26, attackedSquaresByColor.size());
    assertTrue(attackedSquaresByColor.containsAll(coordinates));
  }

  @Test
  void getEnPassantMove1_BLACK() {
    ChessGame chessGame = new ChessGame("rnbqkb1r/ppppp1pp/5n2/8/4Pp1P/3P4/PPP2PP1/RNBQKBNR w KQkq - 1 4");
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_2), new Coordinates(FILE_G, RANK_4)));

    assertEquals(new Coordinates(FILE_G, RANK_3), chessGame.getBoard().getEnPassantMove().get());
  }

  @Test
  void getEnPassantMove2_BLACK() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/p1pp1ppp/4p3/8/1p1P4/2P5/PP2PPPP/RNBQKBNR w KQkq - 0 4");

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_4)));

    assertEquals(new Coordinates(FILE_A, RANK_3), chessGame.getBoard().getEnPassantMove().get());
  }

  @Test
  void getEnPassantMove1_WHITE() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2");

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_7), new Coordinates(FILE_F, RANK_5)));

    assertEquals(new Coordinates(FILE_F, RANK_6), chessGame.getBoard().getEnPassantMove().get());
  }

  @Test
  void getEnPassantMove2_WHITE() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2");

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_D, RANK_7), new Coordinates(FILE_D, RANK_5)));
    System.out.println(chessGame.generateFEN());
    assertEquals(new Coordinates(FILE_D, RANK_6), chessGame.getBoard().getEnPassantMove().get());
  }

  @Test
  void getEnPassantMove_NOMOVE_WHITE() {
    ChessGame chessGame = new ChessGame("rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2");

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_D, RANK_7), new Coordinates(FILE_D, RANK_6)));

    assertTrue(chessGame.getBoard().getEnPassantMove().isEmpty());
  }

  @Test
  void getHalfMoveClock_OnlyPawnMoves() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_D, RANK_2), new Coordinates(FILE_D, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_D, RANK_7), new Coordinates(FILE_D, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_C, RANK_2), new Coordinates(FILE_C, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_C, RANK_4), new Coordinates(FILE_C, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_B, RANK_7), new Coordinates(FILE_B, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_C, RANK_5), new Coordinates(FILE_B, RANK_6)));

    assertEquals(0, chessGame.getBoard().getHalfMoveClock());
  }

  @Test
  void getHalfMoveClock_NoPawnMovesAndNoCaptures() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_1), new Coordinates(FILE_F, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_8), new Coordinates(FILE_F, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_3), new Coordinates(FILE_D, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_6), new Coordinates(FILE_D, RANK_5)));

    assertEquals(4, chessGame.getBoard().getHalfMoveClock());
  }

  @Test
  void getHalfMoveClock_PawnMoveInTheMiddleOfMoves() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_1), new Coordinates(FILE_F, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_8), new Coordinates(FILE_F, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_B, RANK_8), new Coordinates(FILE_C, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_3), new Coordinates(FILE_D, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_6), new Coordinates(FILE_D, RANK_5)));

    assertEquals(3, chessGame.getBoard().getHalfMoveClock());
  }

  @Test
  void getFullMoveNumber() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_1), new Coordinates(FILE_F, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_G, RANK_8), new Coordinates(FILE_F, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_B, RANK_8), new Coordinates(FILE_C, RANK_6)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_3), new Coordinates(FILE_D, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_6), new Coordinates(FILE_D, RANK_5)));

    assertEquals(6, chessGame.getBoard().getFullMoveNumber());
  }

  @Test
  void canCastleKingSide_WHITE() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_H, RANK_2), new Coordinates(FILE_H, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_H, RANK_1), new Coordinates(FILE_H, RANK_2)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_7), new Coordinates(FILE_F, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_H, RANK_2), new Coordinates(FILE_H, RANK_1)));

    assertFalse(chessGame.getBoard().canCastleKingSide(WHITE));
    assertTrue(chessGame.getBoard().canCastleQueenSide(WHITE));
    assertTrue(chessGame.getBoard().canCastleKingSide(BLACK));
    assertTrue(chessGame.getBoard().canCastleQueenSide(BLACK));
  }

  @Test
  void canCastleQueenSide_WHITE() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_1), new Coordinates(FILE_A, RANK_2)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_7), new Coordinates(FILE_F, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_A, RANK_2), new Coordinates(FILE_A, RANK_1)));

    assertTrue(chessGame.getBoard().canCastleKingSide(WHITE));
    assertFalse(chessGame.getBoard().canCastleQueenSide(WHITE));
    assertTrue(chessGame.getBoard().canCastleKingSide(BLACK));
    assertTrue(chessGame.getBoard().canCastleQueenSide(BLACK));
  }

  @Test
  void isKingMoved_WHITE() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_2), new Coordinates(FILE_E, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_1), new Coordinates(FILE_E, RANK_2)));

    assertTrue(chessGame.getBoard().isKingMoved(WHITE));
    assertFalse(chessGame.getBoard().isKingMoved(BLACK));
  }

  @Test
  void isKingMoved_BLACK() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_2), new Coordinates(FILE_E, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_F, RANK_2), new Coordinates(FILE_F, RANK_3)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_8), new Coordinates(FILE_E, RANK_7)));

    assertTrue(chessGame.getBoard().isKingMoved(BLACK));
    assertFalse(chessGame.getBoard().isKingMoved(WHITE));
  }

  @Test
  void isKingMoved_WhiteAndBlack() {
    ChessGame chessGame = new ChessGame();

    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_2), new Coordinates(FILE_E, RANK_4)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_7), new Coordinates(FILE_E, RANK_5)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_1), new Coordinates(FILE_E, RANK_2)));
    chessGame.movePiece(new CoordinatesMove(new Coordinates(FILE_E, RANK_8), new Coordinates(FILE_E, RANK_7)));

    assertTrue(chessGame.getBoard().isKingMoved(WHITE));
    assertTrue(chessGame.getBoard().isKingMoved(BLACK));
  }
}