package com.danielvishnievskyi.chesswebapp.services.player;

import com.danielvishnievskyi.chesswebapp.model.dto.response.PlayerResponseDTO;
import com.danielvishnievskyi.chesswebapp.model.entities.player.Player;

public interface PlayerService {
  PlayerResponseDTO getPlayer(Player player);
  void createPlayer();
}
