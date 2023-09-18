package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.utils.BoardUtils;

import java.util.Set;

public abstract class LongRangePiece extends Piece {
  public LongRangePiece(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public boolean isSquareAvailableForMove(Board board, Coordinates coordinates) {
    boolean squareAvailableForMove = super.isSquareAvailableForMove(board, coordinates);

    return squareAvailableForMove && isSquareAvailableForAttack(board, coordinates);
  }

  @Override
  public boolean isSquareAvailableForAttack(Board board, Coordinates coordinates) {
    Set<Coordinates> coordinatesBetween = BoardUtils.getCoordinatesBetween(this.getCoordinates(), coordinates);

    return coordinatesBetween.stream()
      .allMatch(board::isSquareEmpty);
  }
}
