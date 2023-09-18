package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;

import java.util.Set;

public class Rook extends LongRangePiece implements RookMoves{
  public Rook(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public Set<CoordinatesShift> getMoves() {
    return getRookMoves();
  }
}
