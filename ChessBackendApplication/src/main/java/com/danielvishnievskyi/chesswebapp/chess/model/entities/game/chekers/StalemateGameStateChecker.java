package com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.Piece;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StalemateGameStateChecker extends GameStateChecker {
  @Override
  public GameState check(Board board, Color color) {
    List<Piece> piecesByColor = board.getPiecesByColor(color);

    Set<Piece> pieces = piecesByColor.stream()
      .filter(piece -> !piece.getAvailableMoves(board).isEmpty())
      .collect(Collectors.toSet());

    if (pieces.isEmpty()) {
      return GameState.STALEMATE;
    }

    return GameState.ONGOING;
  }
}
