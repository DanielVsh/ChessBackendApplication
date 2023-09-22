package com.danielvishnievskyi.chesswebapp.chess.model.entities.game;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.board.Board;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers.CheckmateAndCheckGameStateChecker;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers.GameStateChecker;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.chekers.StalemateGameStateChecker;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.CoordinatesMove;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Move;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.Piece;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import com.danielvishnievskyi.chesswebapp.chess.utils.FENUtils;
import com.danielvishnievskyi.chesswebapp.chess.utils.factories.BoardFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class ChessGame {
  private final Board board;

  private final List<GameStateChecker> gameStateCheckers;
  private final List<String> historyFEN;

  @Setter
  private Color currentColorToMove = Color.WHITE;

  public ChessGame(String FEN) {
    this.board = BoardFactory.createBoardByFEN(FEN);
    this.gameStateCheckers = List.of(
      new CheckmateAndCheckGameStateChecker(),
      new StalemateGameStateChecker()
    );
    this.historyFEN = new ArrayList<>();

    currentColorToMove = FEN.contains("w") ? Color.WHITE : Color.BLACK;
  }

  public ChessGame() {
    this.board = BoardFactory.createBoardWithStartPosition();
    this.gameStateCheckers = List.of(
      new CheckmateAndCheckGameStateChecker(),
      new StalemateGameStateChecker()
    );
    this.historyFEN = new ArrayList<>();
  }

  public void movePiece(CoordinatesMove coordinatesMove) {
    Move move = new Move(this.getBoard(), coordinatesMove.getFrom(), coordinatesMove.getTo());

    if (!getGameState().equals(GameState.ONGOING)) {
      throw new RuntimeException("The game is over");
    }

    historyFEN.add(generateFEN());

    Board board = this.getBoard();
    Optional<Piece> optionalPiece = board.getPiece(move.getFrom());

    if (optionalPiece.isEmpty()) {
      throw new RuntimeException("Piece is not in the board");
    }

    Piece piece = optionalPiece.get();

    if (!piece.getColor().equals(this.getCurrentColorToMove())) {
      throw new RuntimeException("You can't play this color");
    }

    if (!piece.getAvailableMoves(board).contains(move.getTo())) {
      throw new RuntimeException("You can't go this way");
    }

    board.removePiece(move.getFrom());
    Optional<Piece> targetPiece = board.getPiece(move.getTo());
    if (targetPiece.isPresent()) {
      this.getBoard().getMovesHistory().add(move);
      board.removePiece(move.getTo());
    }
    board.setPiece(piece, move.getTo());
    this.getBoard().getMovesHistory().add(move);

    this.setCurrentColorToMove(piece.getColor().opposite());
  }

  public String generateFEN() {
    return FENUtils.generateFEN(this);
  }

  public GameState getGameState() {
    return gameStateCheckers.stream()
      .map(checker -> checker.check(board, currentColorToMove))
      .filter(state -> !state.equals(GameState.ONGOING))
      .findFirst()
      .orElse(GameState.ONGOING);
  }
}
