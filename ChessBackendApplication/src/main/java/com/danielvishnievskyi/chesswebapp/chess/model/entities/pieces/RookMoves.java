package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface RookMoves {
  default Set<CoordinatesShift> getRookMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    for (int i = -7; i <= 7; i++) {
      if (i == 0) continue;
      moves.add(new CoordinatesShift(0, i));
      moves.add(new CoordinatesShift(i, 0));
    }

    return moves;
  }
}
