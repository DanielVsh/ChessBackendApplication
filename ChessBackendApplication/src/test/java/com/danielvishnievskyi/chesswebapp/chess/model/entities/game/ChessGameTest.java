package com.danielvishnievskyi.chesswebapp.chess.model.entities.game;

import org.junit.jupiter.api.Test;

class ChessGameTest {

  @Test
  void testGameState() {

    ChessGame chessGame = new ChessGame();
    System.out.println(chessGame.getGameState());
  }

}