package com.danielvishnievskyi.chesswebapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
public class ChessGameMatchRequestDTO {
  private UUID whitePlayerUUID;
  private UUID blackPlayerUUID;

  public String getInMemoryId() {
    return whitePlayerUUID + ":" + blackPlayerUUID;
  }
}
