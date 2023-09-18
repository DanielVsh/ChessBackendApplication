package com.danielvishnievskyi.chesswebapp.chess.model.entities.moves;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesShift {
  private int file, rank;
}
