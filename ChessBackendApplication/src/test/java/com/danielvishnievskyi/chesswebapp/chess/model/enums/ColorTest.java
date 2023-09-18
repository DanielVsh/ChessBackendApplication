package com.danielvishnievskyi.chesswebapp.chess.model.enums;

import org.junit.jupiter.api.Test;

import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.BLACK;
import static com.danielvishnievskyi.chesswebapp.chess.model.enums.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

  @Test
  void opposite() {
    assertEquals(WHITE, BLACK.opposite());
    assertEquals(BLACK, WHITE.opposite());
  }
}