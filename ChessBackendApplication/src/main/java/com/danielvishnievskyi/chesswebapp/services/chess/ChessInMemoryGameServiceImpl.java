package com.danielvishnievskyi.chesswebapp.services.chess;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Move;
import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChessInMemoryGameServiceImpl implements ChessInMemoryGameService {

  @Getter
  private static final Map<String, ChessGame> activeGames = new ConcurrentHashMap<>();

  @Override
  public void newGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    activeGames.put(chessGameMatchRequestDTO.getInMemoryId(), new ChessGame());
  }

  @Override
  public ChessGame getGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    return activeGames.get(chessGameMatchRequestDTO.getInMemoryId());
  }


  @Override
  public ChessGame makeMove(ChessGameMatchRequestDTO chessGameMatchRequestDTO, Move move) {
    ChessGame game = getGame(chessGameMatchRequestDTO);
    game.movePiece(move);
    activeGames.replace(chessGameMatchRequestDTO.getInMemoryId(), game);
    return game;
  }

  @Override
  public void removeGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    activeGames.remove(chessGameMatchRequestDTO.getInMemoryId());
  }
}
