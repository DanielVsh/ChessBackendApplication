package com.danielvishnievskyi.chesswebapp.chess.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardFileTest {

  @Test
  void fromNumber() {
    assertEquals(BoardFile.FILE_A, BoardFile.fromNumber(0));
    assertEquals(BoardFile.FILE_B, BoardFile.fromNumber(1));
    assertEquals(BoardFile.FILE_C, BoardFile.fromNumber(2));
    assertEquals(BoardFile.FILE_D, BoardFile.fromNumber(3));
    assertEquals(BoardFile.FILE_E, BoardFile.fromNumber(4));
    assertEquals(BoardFile.FILE_F, BoardFile.fromNumber(5));
    assertEquals(BoardFile.FILE_G, BoardFile.fromNumber(6));
    assertEquals(BoardFile.FILE_H, BoardFile.fromNumber(7));
  }
}