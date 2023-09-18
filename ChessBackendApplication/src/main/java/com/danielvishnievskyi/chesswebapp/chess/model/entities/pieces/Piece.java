package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public abstract class Piece {
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
    return board.isSquareEmpty(coordinates) || !board.getPiece(coordinates).get().getColor().equals(color);
  }

  public boolean isSquareAvailableForAttack(Board board, Coordinates coordinates) {
    return true;
  }
}
