package com.danielvishnievskyi.chesswebapp.chess.model.entities.moves;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesMove {
  private Coordinates from, to;
}
