package com.danielvishnievskyi.chesswebapp.chess.utils.factories;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardFactoryTest {

  @Test
  void setBoardByFEN() {
    Board board = BoardFactory.createBoardByFEN("k7/p7/8/8/8/8/7P/7K w - - 0 1");

    assertEquals("k7/p7/8/8/8/8/7P/7K w - - 0 1", board.getInitialFEN());
  }
}