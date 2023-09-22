package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public abstract class Piece implements Cloneable{
  private Coordinates coordinates;
  private Color color;

  public abstract Set<CoordinatesShift> getMoves();

  public Set<CoordinatesShift> getAttackMoves() {
    return getMoves();
  }

  public Set<Coordinates> getAvailableMoves(Board board) {
    return getMoves().stream()
      .filter(coordinatesShift -> coordinates.isShiftWithinBoardBounds(coordinatesShift))
      .map(coordinatesShift -> coordinates.shift(coordinatesShift))
      .filter(shiftedCoordinates -> isSquareAvailableForMove(board, shiftedCoordinates))
      .collect(Collectors.toSet());
  }

  public Set<Coordinates> getAvailableAttackMoves(Board board) {
    return getAttackMoves().stream()
      .filter(coordinatesShift -> coordinates.isShiftWithinBoardBounds(coordinatesShift))
      .map(coordinatesShift -> coordinates.shift(coordinatesShift))
      .filter(shiftedCoordinates -> isSquareAvailableForAttack(board, shiftedCoordinates))
      .collect(Collectors.toSet());
  }

  public boolean isSquareAvailableForMove(Board board, Coordinates coordinates) {
    boolean isMovePossible = board.isSquareEmpty(coordinates) || !board.getPiece(coordinates).get().getColor().equals(color);

    return isMovePossible && !isKingAttackedAfterMove(board, coordinates);
  }

  public boolean isSquareAvailableForAttack(Board board, Coordinates coordinates) {
    return true;
  }

  protected final boolean isKingAttackedAfterMove(Board board, Coordinates coordinates) {
    Coordinates currentPieceCoordinate = this.getCoordinates();
    board.removePiece(currentPieceCoordinate);

    Optional<Piece> possiblePieceInMovedSquare = board.getPiece(coordinates);
    possiblePieceInMovedSquare.ifPresent(piece -> board.removePiece(piece.getCoordinates()));
    board.setPiece(this, coordinates);

    Piece king = board.getPiecesByColor(this.getColor()).stream()
      .filter(piece -> piece instanceof King)
      .findFirst().get();

    boolean isKingAttackedAfterMove = board.isSquareAttackedByColor(this.getColor().opposite(), king.getCoordinates());

    board.removePiece(coordinates);
    possiblePieceInMovedSquare.ifPresent(piece -> board.setPiece(piece, piece.getCoordinates()));
    board.setPiece(this, currentPieceCoordinate);

    return isKingAttackedAfterMove;
  }
}
