package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece{
  public King(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public Set<CoordinatesShift> getMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    moves.add(new CoordinatesShift(0, 1));
    moves.add(new CoordinatesShift(1, 0));
    moves.add(new CoordinatesShift(-1, 0));
    moves.add(new CoordinatesShift(0, -1));
    moves.add(new CoordinatesShift(-1, 1));
    moves.add(new CoordinatesShift(1, 1));
    moves.add(new CoordinatesShift(-1, -1));
    moves.add(new CoordinatesShift(1, -1));

    return moves;
  }

  @Override
  public boolean isSquareAvailableForMove(Board board, Coordinates coordinates) {
    return super.isSquareAvailableForMove(board, coordinates) &&
      !board.getAttackedSquaresByColor(this.getColor().opposite()).contains(coordinates);
  }
}
