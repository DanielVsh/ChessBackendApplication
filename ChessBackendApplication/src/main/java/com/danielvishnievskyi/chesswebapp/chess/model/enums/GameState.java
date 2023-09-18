package com.danielvishnievskyi.chesswebapp.chess.model.enums;

public enum GameState {
  ONGOING,
  CHECK,
  CHECKMATE,
  STALEMATE,
  DRAW_BY_INSUFFICIENT_MATERIAL,
  DRAW_BY_FIFTY_MOVES_RULE,
  DRAW_BY_THREEFOLD_REPETITION,
  RESIGNATION,
  TIMEOUT,
  ABANDONED,
  DRAW_AGREEMENT,
  ERROR;

  @Override
  public String toString() {
    return switch (this) {
      case ONGOING -> "Ongoing";
      case CHECK -> "Check";
      case CHECKMATE -> "Checkmate";
      case STALEMATE -> "Stalemate";
      case DRAW_BY_INSUFFICIENT_MATERIAL -> "Draw by Insufficient Material";
      case DRAW_BY_FIFTY_MOVES_RULE -> "Draw by Fifty Moves Rule";
      case DRAW_BY_THREEFOLD_REPETITION -> "Draw by Threefold Repetition";
      case RESIGNATION -> "Resignation";
      case TIMEOUT -> "Timeout";
      case ABANDONED -> "Abandoned";
      case DRAW_AGREEMENT -> "Draw by Agreement";
      case ERROR -> "Error";
    };
  }
}
