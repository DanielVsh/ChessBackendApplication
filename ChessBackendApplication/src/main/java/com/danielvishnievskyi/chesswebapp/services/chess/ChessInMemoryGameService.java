package com.danielvishnievskyi.chesswebapp.services.chess;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Move;
import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;

public interface ChessInMemoryGameService {
  void newGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO);

  ChessGame getGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO);

  ChessGame makeMove(ChessGameMatchRequestDTO chessGameMatchRequestDTO, Move move);

  void removeGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO);
}
