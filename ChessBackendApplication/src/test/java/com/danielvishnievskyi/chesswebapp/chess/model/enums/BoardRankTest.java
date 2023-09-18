package com.danielvishnievskyi.chesswebapp.chess.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardRankTest {

  @Test
  void fromNumber() {
    assertEquals(BoardRank.RANK_1, BoardRank.fromNumber(0));
    assertEquals(BoardRank.RANK_2, BoardRank.fromNumber(1));
    assertEquals(BoardRank.RANK_3, BoardRank.fromNumber(2));
    assertEquals(BoardRank.RANK_4, BoardRank.fromNumber(3));
    assertEquals(BoardRank.RANK_5, BoardRank.fromNumber(4));
    assertEquals(BoardRank.RANK_6, BoardRank.fromNumber(5));
    assertEquals(BoardRank.RANK_7, BoardRank.fromNumber(6));
    assertEquals(BoardRank.RANK_8, BoardRank.fromNumber(7));
  }
}