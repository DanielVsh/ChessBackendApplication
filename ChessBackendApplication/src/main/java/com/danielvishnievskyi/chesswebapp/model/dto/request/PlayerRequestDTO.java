package com.danielvishnievskyi.chesswebapp.model.dto.request;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class PlayerRequestDTO {
  private UUID uuid;
}
