package com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import com.danielvishnievskyi.chesswebapp.chess.utils.factories.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StalemateChessGameStateCheckerTest {

  private StalemateGameStateChecker stalemateGameStateChecker;

  @BeforeEach
  void init(){
    stalemateGameStateChecker = new StalemateGameStateChecker();
  }

  @Test
  void check_KingWithoutPossibleMoves_STALEMATE() {
    Board board = BoardFactory.createBoardByFEN("k1r1r3/8/8/8/8/8/7r/3K4 w - - 0 1");

    GameState check = stalemateGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.STALEMATE, check);
  }

  @Test
  void check_KingAndPawnWithoutPossibleMoves_STALEMATE() {
    Board board = BoardFactory.createBoardByFEN("k1r1r3/8/7p/7P/8/8/7r/3K4 w - - 0 1");

    GameState check = stalemateGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.STALEMATE, check);
  }

  @Test
  void check_KingAndPawnWithPossibleMoves_ONGOING() {
    Board board = BoardFactory.createBoardByFEN("k1r1r3/8/8/7P/8/8/7r/3K4 w - - 0 1");

    GameState check = stalemateGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.ONGOING, check);
  }
}