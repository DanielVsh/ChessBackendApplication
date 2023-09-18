package com.danielvishnievskyi.chesswebapp.chess.utils.factories;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.*;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;

public class PieceFactory {
  public static Piece fromFenChar(char fenChar, Coordinates coordinates) {
    return switch (fenChar) {
      case 'p' -> new Pawn(coordinates, BLACK);
      case 'P' -> new Pawn(coordinates, WHITE);
      case 'r' -> new Rook(coordinates, BLACK);
      case 'R' -> new Rook(coordinates, WHITE);
      case 'n' -> new Knight(coordinates, BLACK);
      case 'N' -> new Knight(coordinates, WHITE);
      case 'b' -> new Bishop(coordinates, BLACK);
      case 'B' -> new Bishop(coordinates, WHITE);
      case 'q' -> new Queen(coordinates, BLACK);
      case 'Q' -> new Queen(coordinates, WHITE);
      case 'k' -> new King(coordinates, BLACK);
      case 'K' -> new King(coordinates, WHITE);
      default -> throw new RuntimeException("Unknown FEN char!");
    };
  }
}
