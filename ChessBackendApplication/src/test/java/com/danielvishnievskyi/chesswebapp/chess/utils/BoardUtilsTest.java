package com.danielvishnievskyi.chesswebapp.chess.utils;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.utils.BoardUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardUtilsTest {

  @Test
  void getDiagonalCoordinatesBetween_FromBottomToTop() {
    Set<Coordinates> diagonalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_C, BoardRank.RANK_3),
      new Coordinates(BoardFile.FILE_E, BoardRank.RANK_5)
    );

    assertEquals(1, diagonalCoordinatesBetween.size());
    assertTrue(diagonalCoordinatesBetween.contains(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_4)
    ));
  }

  @Test
  void getDiagonalCoordinatesBetween_FromTopToBottom() {
    Set<Coordinates> diagonalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_A, BoardRank.RANK_7),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_2)
    );

    assertEquals(4, diagonalCoordinatesBetween.size());
    assertTrue(diagonalCoordinatesBetween.containsAll(List.of(
        new Coordinates(BoardFile.FILE_B, BoardRank.RANK_6),
        new Coordinates(BoardFile.FILE_C, BoardRank.RANK_5),
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_4),
        new Coordinates(BoardFile.FILE_E, BoardRank.RANK_3)
      )
    ));
  }

  @Test
  void getVerticalCoordinatesBetween_FromTopToBottom() {
    Set<Coordinates> verticalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_6),
      new Coordinates(BoardFile.FILE_D, BoardRank.RANK_2)
    );

    assertEquals(3, verticalCoordinatesBetween.size());
    assertTrue(verticalCoordinatesBetween.containsAll(List.of(
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_5),
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_3),
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_4)
      )
    ));
  }

  @Test
  void getVerticalCoordinatesBetween_FromBottomToTop() {
    Set<Coordinates> verticalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_H, BoardRank.RANK_1),
      new Coordinates(BoardFile.FILE_H, BoardRank.RANK_8)
    );

    assertEquals(6, verticalCoordinatesBetween.size());
    assertTrue(verticalCoordinatesBetween.containsAll(List.of(
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_2),
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_3),
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_4),
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_6),
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_5),
        new Coordinates(BoardFile.FILE_H, BoardRank.RANK_7)
      )
    ));
  }

  @Test
  void getHorizontalCoordinatesBetween_FromLeftToRight() {
    Set<Coordinates> horizontalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_B, BoardRank.RANK_4),
      new Coordinates(BoardFile.FILE_F, BoardRank.RANK_4)
    );

    assertEquals(3, horizontalCoordinatesBetween.size());
    assertTrue(horizontalCoordinatesBetween.containsAll(List.of(
        new Coordinates(BoardFile.FILE_C, BoardRank.RANK_4),
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_4),
        new Coordinates(BoardFile.FILE_E, BoardRank.RANK_4)
      )
    ));
  }

  @Test
  void getHorizontalCoordinatesBetween_FromRightToLeft() {
    Set<Coordinates> horizontalCoordinatesBetween = BoardUtils.getCoordinatesBetween(
      new Coordinates(BoardFile.FILE_H, BoardRank.RANK_6),
      new Coordinates(BoardFile.FILE_C, BoardRank.RANK_6)
    );

    assertEquals(4, horizontalCoordinatesBetween.size());
    assertTrue(horizontalCoordinatesBetween.containsAll(List.of(
        new Coordinates(BoardFile.FILE_G, BoardRank.RANK_6),
        new Coordinates(BoardFile.FILE_E, BoardRank.RANK_6),
        new Coordinates(BoardFile.FILE_D, BoardRank.RANK_6),
        new Coordinates(BoardFile.FILE_F, BoardRank.RANK_6)
      )
    ));
  }
}