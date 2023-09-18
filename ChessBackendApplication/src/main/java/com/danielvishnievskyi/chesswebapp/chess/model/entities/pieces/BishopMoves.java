package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface BishopMoves {
  default Set<CoordinatesShift> getBishopMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    for (int i = -7; i <= 7; i++) {
      if (i == 0) continue;
      moves.add(new CoordinatesShift(i, i));
      moves.add(new CoordinatesShift(-i, i));
    }

    return moves;
  }
}
