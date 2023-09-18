package com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;

public abstract class GameStateChecker {
  public abstract GameState check(Board board, Color color);
}
