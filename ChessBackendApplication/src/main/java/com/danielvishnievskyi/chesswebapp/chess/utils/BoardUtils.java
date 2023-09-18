package com.danielvishnievskyi.chesswebapp.chess.utils;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;

import java.util.HashSet;
import java.util.Set;

public class BoardUtils {
  public static Set<Coordinates> getCoordinatesBetween(Coordinates from, Coordinates to) {
    Set<Coordinates> result = new HashSet<>();

    int fileShift = Integer.compare(to.getFile().toInt(), from.getFile().toInt());
    int rankShift = Integer.compare(to.getRank().toInt(), from.getRank().toInt());

    int fileIndex = from.getFile().toInt() + fileShift;
    int rankIndex = from.getRank().toInt() + rankShift;

    while (fileIndex != to.getFile().toInt() || rankIndex != to.getRank().toInt()) {
      result.add(new Coordinates(BoardFile.fromNumber(fileIndex), BoardRank.fromNumber(rankIndex)));

      fileIndex += fileShift;
      rankIndex += rankShift;
    }

    return result;
  }

}
