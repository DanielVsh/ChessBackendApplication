package com.danielvishnievskyi.chesswebapp.chess.model.enums;

public enum BoardRank {
  RANK_1(0),
  RANK_2(1),
  RANK_3(2),
  RANK_4(3),
  RANK_5(4),
  RANK_6(5),
  RANK_7(6),
  RANK_8(7);

  private final int rank;

  BoardRank(int rank) {
    this.rank = rank;
  }

  public static BoardRank fromNumber(int number) {
    return BoardRank.values()[number];
  }

  public int toInt() {
    return rank;
  }

  @Override
  public String toString() {
    return super.toString().substring(5);
  }
}
