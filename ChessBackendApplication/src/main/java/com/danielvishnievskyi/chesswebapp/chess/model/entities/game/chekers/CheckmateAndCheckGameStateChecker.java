package com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.King;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.LongRangePiece;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.Piece;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckmateAndCheckGameStateChecker extends GameStateChecker {
  @Override
  public GameState check(Board board, Color color) {
    Piece king = board.getPieces().stream()
      .filter(piece -> piece instanceof King)
      .filter(piece -> piece.getColor().equals(color))
      .findFirst().get();

    boolean isKingChecked = board.isSquareAttackedByColor(king.getColor().opposite(), king.getCoordinates());

    if (isKingChecked && king.getAvailableMoves(board).isEmpty() && !isKingCanBeDefended(board, (King) king)) {
      return GameState.CHECKMATE;
    }

    if (isKingChecked) {
      return GameState.CHECK;
    }

    return GameState.ONGOING;
  }

  private boolean isKingCanBeDefended(Board board, King king) {
    List<Piece> piecesByKingColor = board.getPiecesByColor(king.getColor());
    List<Piece> piecesByKingOppositeColor = board.getPiecesByColor(king.getColor().opposite());

    Set<Piece> piecesThatAttackKing = piecesByKingOppositeColor.stream()
      .filter(piece -> piece instanceof LongRangePiece)
      .filter(piece -> piece.getAvailableAttackMoves(board).contains(king.getCoordinates()))
      .collect(Collectors.toSet());

    if (piecesThatAttackKing.size() > 1) {
      return false;
    }

    Set<Coordinates> attackLines = piecesThatAttackKing.stream()
      .flatMap(piece -> piece.getAvailableMoves(board).stream())
      .collect(Collectors.toSet());

    Set<Piece> piecesThatCanDefendTheKing = piecesByKingColor.stream()
      .filter(piece -> piece.getAvailableMoves(board).stream()
        .anyMatch(attackLines::contains))
      .collect(Collectors.toSet());

    if (piecesThatCanDefendTheKing.isEmpty()) {
      return false;
    }

    return true;
  }
}
