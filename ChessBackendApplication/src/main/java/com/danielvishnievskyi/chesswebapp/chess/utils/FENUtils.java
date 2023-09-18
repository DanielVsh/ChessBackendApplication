package com.danielvishnievskyi.chesswebapp.chess.utils;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.*;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;

import java.util.Optional;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;

public class FENUtils {
  public static String generateFEN(ChessGame chessGame) {
    Board board = chessGame.getBoard();
    StringBuilder fen = new StringBuilder();

    fen.append(generateBoardFEN(board)).append(" ");

    fen.append(chessGame.getCurrentColorToMove() == Color.WHITE ? "w" : "b").append(" ");

    fen.append(generateCastlingRightsFEN(board)).append(" ");

    Optional<Coordinates> enPassantMove = board.getEnPassantMove();
    String enPassantString = enPassantMove
      .map(coordinates -> coordinates.getFile().toString() + coordinates.getRank().toString())
      .orElse("-");
    fen.append(enPassantString).append(" ");

    fen.append(board.getHalfMoveClock()).append(" ");

    fen.append(board.getFullMoveNumber());

    return fen.toString();
  }

  private static String generateBoardFEN(Board board) {
    StringBuilder fen = new StringBuilder();

    for (int rank = 7; rank >= 0; rank--) {
      int emptySquares = 0;
      for (int file = 0; file < 8; file++) {
        Optional<Piece> piece = board.getPiece(new Coordinates(BoardFile.fromNumber(file), BoardRank.fromNumber(rank)));
        if (piece.isEmpty()) {
          emptySquares++;
        } else {
          if (emptySquares > 0) {
            fen.append(emptySquares);
            emptySquares = 0;
          }
          fen.append(pieceToFEN(piece.get()));
        }
      }

      if (emptySquares > 0) {
        fen.append(emptySquares);
      }

      if (rank > 0) {
        fen.append("/");
      }
    }

    return fen.toString();
  }

  private static String generateCastlingRightsFEN(Board board) {
    StringBuilder fen = new StringBuilder();

    fen.append(board.canCastleKingSide(Color.WHITE) ? "K" : "");
    fen.append(board.canCastleQueenSide(Color.WHITE) ? "Q" : "");
    fen.append(board.canCastleKingSide(Color.BLACK) ? "k" : "");
    fen.append(board.canCastleQueenSide(Color.BLACK) ? "q" : "");

    if (fen.isEmpty()) {
      fen.append("-");
    }

    return fen.toString();
  }

  private static Character pieceToFEN(Piece piece) {
    if (piece instanceof Pawn) {
      if (piece.getColor().equals(WHITE)) return 'P';
      else return 'p';
    } else if (piece instanceof Rook) {
      if (piece.getColor().equals(WHITE)) return 'R';
      else return 'r';
    } else if (piece instanceof Knight) {
      if (piece.getColor().equals(WHITE)) return 'N';
      else return 'n';
    } else if (piece instanceof Bishop) {
      if (piece.getColor().equals(WHITE)) return 'B';
      else return 'b';
    } else if (piece instanceof Queen) {
      if (piece.getColor().equals(WHITE)) return 'Q';
      else return 'q';
    } else {
      if (piece.getColor().equals(WHITE)) return 'K';
      else return 'k';
    }
  }
}
