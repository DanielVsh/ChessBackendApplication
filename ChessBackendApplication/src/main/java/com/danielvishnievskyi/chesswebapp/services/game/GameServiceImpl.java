package com.danielvishnievskyi.chesswebapp.services.game;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.game.ChessGame;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import com.danielvishnievskyi.chesswebapp.model.dto.request.ChessGameMatchRequestDTO;
import com.danielvishnievskyi.chesswebapp.model.dto.request.PlayerRequestDTO;
import com.danielvishnievskyi.chesswebapp.model.dto.response.GameResponseDTO;
import com.danielvishnievskyi.chesswebapp.model.entities.game.Game;
import com.danielvishnievskyi.chesswebapp.model.entities.player.Player;
import com.danielvishnievskyi.chesswebapp.repositories.GameRepository;
import com.danielvishnievskyi.chesswebapp.repositories.PlayerRepository;
import com.danielvishnievskyi.chesswebapp.services.chess.ChessInMemoryGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;
  private final ChessInMemoryGameService chessInMemoryGameService;
  private final PlayerRepository playerRepository;

  @Override
  public Page<GameResponseDTO> getGames(Pageable pageable, PlayerRequestDTO player) {
    return null;
  }

  @Override
  public GameResponseDTO getGame() {
    return null;
  }

  @Override
  public void createGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    chessInMemoryGameService.newGame(chessGameMatchRequestDTO);
  }

  @Override
  public void saveGame(ChessGameMatchRequestDTO chessGameMatchRequestDTO) {
    ChessGame chessGame = chessInMemoryGameService.getGame(chessGameMatchRequestDTO);

    Player winner = null;

    Player whitePlayer = playerRepository.findById(chessGameMatchRequestDTO.getWhitePlayerUUID()).orElseThrow();
    Player blackPlayer = playerRepository.findById(chessGameMatchRequestDTO.getBlackPlayerUUID()).orElseThrow();

    if (chessGame.getGameState().equals(GameState.CHECKMATE)) {
      winner = chessGame.getHistoryFEN().get(chessGame.getHistoryFEN().size() - 1).contains("w")
        ? whitePlayer
        : blackPlayer;
    }

    Game game = Game.builder()
      .whitePlayer(whitePlayer)
      .blackPlayer(blackPlayer)
      .winner(winner)
      .gameResult(chessGame.getGameState())
      .date(LocalDateTime.now())
      .history(chessGame.getHistoryFEN())
      .build();

    gameRepository.save(game);
  }
}
