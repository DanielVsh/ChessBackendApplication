package com.danielvishnievskyi.chesswebapp.model.entities.player;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false)
  private UUID uuid;

  @Column(unique = true)
  private String username;

  private String password;

  @Column(unique = true)
  private String nickname;

  private int rating;
}
