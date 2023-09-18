package com.danielvishnievskyi.chesswebapp.chess.model.enums;

public enum Color {
  WHITE,
  BLACK;

  public Color opposite() {
    return this == WHITE ? BLACK : WHITE;
  }
}
