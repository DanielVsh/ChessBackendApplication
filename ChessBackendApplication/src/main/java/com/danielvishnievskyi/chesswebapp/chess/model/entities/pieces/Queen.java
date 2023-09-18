package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Queen extends LongRangePiece implements RookMoves, BishopMoves{
  public Queen(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public Set<CoordinatesShift> getMoves() {
    Set<CoordinatesShift> moves = new HashSet<>(getBishopMoves());
    moves.addAll(getRookMoves());
    return moves;
  }
}
