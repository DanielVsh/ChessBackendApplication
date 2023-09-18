package com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesShift;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Move;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.utils.BoardUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pawn extends Piece {
  public Pawn(Coordinates coordinates, Color color) {
    super(coordinates, color);
  }

  @Override
  public Set<CoordinatesShift> getMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    if (this.getColor().equals(Color.WHITE)) {
      if (this.getCoordinates().getRank().equals(BoardRank.RANK_2)) {
        moves.add(new CoordinatesShift(0, 2));
      }
      moves.add(new CoordinatesShift(0, 1));

      moves.add(new CoordinatesShift(1, 1));
      moves.add(new CoordinatesShift(-1, 1));
    }

    if (this.getColor().equals(Color.BLACK)) {
      if (this.getCoordinates().getRank().equals(BoardRank.RANK_7)) {
        moves.add(new CoordinatesShift(0, -2));
      }
      moves.add(new CoordinatesShift(0, -1));

      moves.add(new CoordinatesShift(1, -1));
      moves.add(new CoordinatesShift(-1, -1));
    }

    return moves;
  }

  @Override
  public Set<CoordinatesShift> getAttackMoves() {
    Set<CoordinatesShift> moves = new HashSet<>();

    if (this.getColor().equals(Color.WHITE)) {
      moves.add(new CoordinatesShift(1, 1));
      moves.add(new CoordinatesShift(-1, 1));
    }

    if (this.getColor().equals(Color.BLACK)) {
      moves.add(new CoordinatesShift(1, -1));
      moves.add(new CoordinatesShift(-1, -1));
    }

    return moves;
  }

  @Override
  public boolean isSquareAvailableForMove(Board board, Coordinates coordinates) {
    if (this.getCoordinates().getFile().equals(coordinates.getFile())) {
      int rankShift = Math.abs(this.getCoordinates().getRank().toInt() - coordinates.getRank().toInt());

      if (rankShift == 2) {
        Set<Coordinates> coordinatesBetween = BoardUtils.getCoordinatesBetween(this.getCoordinates(), coordinates);
        return coordinatesBetween.stream().allMatch(board::isSquareEmpty);
      } else {
        return board.isSquareEmpty(coordinates);
      }
    } else {
      if (isEnPassantPossible(board, coordinates)) {
        return true;
      }

      return !board.isSquareEmpty(coordinates) &&
        board.getPiece(coordinates).filter(piece -> !piece.getColor().equals(this.getColor())).isPresent();
    }
  }

  private boolean isEnPassantPossible(Board board, Coordinates targetCoordinates) {
    if (this.getColor().equals(Color.WHITE) && !this.getCoordinates().getRank().equals(BoardRank.RANK_5)) {
      return false;
    }

    if (this.getColor().equals(Color.BLACK) && !this.getCoordinates().getRank().equals(BoardRank.RANK_4)) {
      return false;
    }
    List<Move> movesHistory = board.getMovesHistory();

    if (movesHistory.isEmpty()) {
      return false;
    }

    Move lastMove = movesHistory.get(movesHistory.size() - 1);

    Piece lastMovedPiece = lastMove.getPieceToMove();

    if (lastMovedPiece instanceof Pawn) {
      Coordinates from = lastMove.getFrom();
      Coordinates to = lastMove.getTo();

      if (Math.abs(from.getRank().toInt() - to.getRank().toInt()) == 2) {
        Coordinates expectedEnPassantSquare = calculateEnPassantSquare(lastMove.getTo());
        return expectedEnPassantSquare.equals(targetCoordinates);
      }
    }

    return false;
  }

  private Coordinates calculateEnPassantSquare(Coordinates lastPawnMoveDestination) {
    int i = this.getColor().equals(Color.WHITE) ? 1 : -1;
    int rank = lastPawnMoveDestination.getRank().toInt() + i;
    int file = lastPawnMoveDestination.getFile().toInt();

    return new Coordinates(BoardFile.fromNumber(file), BoardRank.fromNumber(rank));
  }
}
