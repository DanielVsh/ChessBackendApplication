package com.danielvishnievskyi.chesswebapp.repositories;

import com.danielvishnievskyi.chesswebapp.model.entities.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID>, JpaSpecificationExecutor<Player> {
  Optional<Player> findByUsername(String username);
}