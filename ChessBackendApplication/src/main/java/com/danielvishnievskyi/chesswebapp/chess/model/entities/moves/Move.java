package com.danielvishnievskyi.chesswebapp.chess.model.entities.moves;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.Piece;
import lombok.Data;

import java.util.Optional;

@Data
public class Move {
  private Coordinates from, to;
  private Piece pieceToMove;
  private Optional<Piece> capturedPiece;

  public Move(Board board, Coordinates from, Coordinates to) {
    this.from = from;
    this.to = to;
    this.pieceToMove = board.getPiece(from).get();
    this.capturedPiece = board.getPiece(to);
  }

  public boolean isCaptured() {
    return capturedPiece.isPresent();
  }
}
