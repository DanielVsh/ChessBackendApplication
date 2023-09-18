package com.danielvishnievskyi.chesswebapp.chess.model.enums;

public enum BoardFile {
  FILE_A(0),
  FILE_B(1),
  FILE_C(2),
  FILE_D(3),
  FILE_E(4),
  FILE_F(5),
  FILE_G(6),
  FILE_H(7);

  private final int file;

  BoardFile(int file) {
    this.file = file;
  }

  public static BoardFile fromNumber(int number) {
    return BoardFile.values()[number];
  }

  @Override
  public String toString() {
    return super.toString().substring(5).toLowerCase();
  }

  public int toInt() {
    return file;
  }
}
