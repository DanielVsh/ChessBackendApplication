package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece{
  public Knight(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public Set<CoordinatesShift> getMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    moves.add(new CoordinatesShift(1, 2));
    moves.add(new CoordinatesShift(-1, 2));
    moves.add(new CoordinatesShift(1, -2));
    moves.add(new CoordinatesShift(-1, -2));

    moves.add(new CoordinatesShift(2, 1));
    moves.add(new CoordinatesShift(-2, 1));
    moves.add(new CoordinatesShift(2, -1));
    moves.add(new CoordinatesShift(-2, -1));

    return moves;
  }
}
