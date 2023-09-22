package com.danielvishnievskyi.chesswebapp.chess.model.entities.game;

import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessGameTest {

  @Test
  void testGameState_Check() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/2K5/8/8/5q2 w - - 0 1");

    assertEquals(GameState.CHECK, chessGame.getGameState());
  }

  @Test
  void testGameState_Checkmate() {
    ChessGame chessGame = new ChessGame("7k/8/8/8/8/8/5q2/K4q2 w - - 0 1");

    assertEquals(GameState.CHECKMATE, chessGame.getGameState());
  }

}