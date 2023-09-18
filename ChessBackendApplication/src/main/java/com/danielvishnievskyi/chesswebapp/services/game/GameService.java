package com.danielvishnievskyi.chesswebapp.services.game;

import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import com.danielvishnievskyi.chesswebapp.model.dto.request.PlayerRequestDTO;
import com.danielvishnievskyi.chesswebapp.model.dto.response.GameResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GameService {
  Page<GameResponseDTO> getGames(Pageable pageable, PlayerRequestDTO player);

  GameResponseDTO getGame();

  void createGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO);

  void saveGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO);
}
