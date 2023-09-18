package com.danielvishnievskyi.chesswebapp.model.entities.game;

import com.danielvishnievskyi.chesswebapp.chess.model.enums.GameState;
import com.danielvishnievskyi.chesswebapp.model.entities.player.Player;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false)
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "white_player_id", nullable = false)
  private Player whitePlayer;

  @ManyToOne
  @JoinColumn(name = "black_player_id", nullable = false)
  private Player blackPlayer;

  @ManyToOne
  @JoinColumn(name = "winner_player_id")
  private Player winner;

  private GameState gameResult;

  private LocalDateTime date;

  @ElementCollection
  private List<String> history;
}
