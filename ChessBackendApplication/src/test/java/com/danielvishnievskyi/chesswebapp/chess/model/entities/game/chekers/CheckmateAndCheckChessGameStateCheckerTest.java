package com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import com.danielvishnievskyi.chesswebapp.chess.utils.factories.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckmateAndCheckChessGameStateCheckerTest {
  CheckmateAndCheckGameStateChecker checkmateAndCheckGameStateChecker;
  @BeforeEach
  void init() {
    checkmateAndCheckGameStateChecker = new CheckmateAndCheckGameStateChecker();
  }

  @Test
  void check_LongRangePieceAttacks_CHECK() {
    Board board = BoardFactory.createBoardByFEN("k7/p7/8/3r4/8/8/8/3K4 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.CHECK, check);
  }

  @Test
  void check_LongRangePiecesAttack_CHEKMATE(){
    Board board = BoardFactory.createBoardByFEN("k7/p7/8/2rrr3/8/8/8/3K4 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.CHECKMATE, check);
  }

  @Test
  void check_LongRangePiecesAttackCanBeDefended_CHECK() {
    Board board = BoardFactory.createBoardByFEN("k7/p7/8/2rrr3/8/7R/8/3K4 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.CHECK, check);
  }

  @Test
  void check_LongRangePiecesAttackCanBeDefended2_CHECK() {
    Board board = BoardFactory.createBoardByFEN("k7/8/8/8/b1nnn3/2n2n2/8/3KN3 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.CHECK, check);
  }

  @Test
  void check_LongRangePiecesAttackCantBeDefended_CHEMATE() {
    Board board = BoardFactory.createBoardByFEN("k1r1r3/8/8/7b/b7/R7/7r/3K4 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.CHECKMATE, check);
  }

  @Test
  void check_PiecesDoesntAttackKing_ONGOING() {
    Board board = BoardFactory.createBoardByFEN("k7/8/8/8/4p3/2ppp3/1p3p2/3KN3 w - - 0 1");

    GameState check = checkmateAndCheckGameStateChecker.check(board, Color.WHITE);

    assertEquals(GameState.ONGOING, check);
  }
}