package com.danielvishnievskyi.chesswebapp.services.player;

import com.danielvishnievskyi.chesswebapp.model.dto.response.PlayerResponseDTO;
import com.danielvishnievskyi.chesswebapp.model.entities.player.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService{
  @Override
  public PlayerResponseDTO getPlayer(Player player) {
    return null;
  }

  @Override
  public void createPlayer() {

  }
}
