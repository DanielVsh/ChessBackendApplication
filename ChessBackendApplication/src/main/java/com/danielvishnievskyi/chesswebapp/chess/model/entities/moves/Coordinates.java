package com.danielvishnievskyi.chesswebapp.chess.model.entities.moves;

import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {
  private BoardFile file;
  private BoardRank rank;

  public Coordinates shift(CoordinatesShift coordinatesShift) {
    return new Coordinates(
      BoardFile.fromNumber(this.file.toInt() + coordinatesShift.getFile()), BoardRank.fromNumber(this.rank.toInt() + coordinatesShift.getRank())
    );
  }

  public boolean isShiftWithinBoardBounds(CoordinatesShift coordinatesShift) {
    int rankShift = coordinatesShift.getRank();
    int fileShift = coordinatesShift.getFile();

    int newRankPosition = rankShift + rank.toInt();
    int newFilePosition = fileShift + file.toInt();
    return (newRankPosition >= 0 && newRankPosition <= 7) && (newFilePosition >= 0 && newFilePosition <= 7);
  }
}
