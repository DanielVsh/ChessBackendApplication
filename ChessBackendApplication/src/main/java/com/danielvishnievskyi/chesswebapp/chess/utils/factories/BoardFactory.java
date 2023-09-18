package com.danielvishnievskyi.chesswebapp.chess.utils.factories;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;

public class BoardFactory {

  public static Board createBoardByFEN(String fen) {
    Board board = new Board(fen);

    String[] parts = fen.split(" ");
    if (parts.length != 6) {
      throw new IllegalArgumentException("Invalid FEN format.");
    }
    String piecePositions = parts[0];

    String[] fenRows = piecePositions.split("/");
    int rank = 7;

    for (String row : fenRows) {
      int fileIndex = 0;
      for (char fenChar : row.toCharArray()) {
        if (Character.isDigit(fenChar)) {
          fileIndex += Character.getNumericValue(fenChar);
        } else {
          Coordinates coordinates = new Coordinates(BoardFile.fromNumber(fileIndex), BoardRank.fromNumber(rank));
          board.setPiece(PieceFactory.fromFenChar(fenChar, coordinates), coordinates);
          fileIndex++;
        }
      }
      rank--;
    }

    return board;
  }

  public static Board createBoardWithStartPosition() {
    return new Board();
  }
}
