package com.danielvishnievskyi.chesswebapp.chess.model.entities.board;

import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Coordinates;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.moves.Move;
import com.danielvishnievskyi.chesswebapp.chess.model.entities.pieces.*;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardFile;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.BoardRank;
import com.danielvishnievskyi.chesswebapp.chess.model.enums.Color;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Board {
  private final List<Piece> pieces;
  private final List<Move> movesHistory;

  private String initialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

  public Board() {
    this.pieces = new ArrayList<>();
    this.movesHistory = new ArrayList<>();
    startPosition();
  }

  public Board(String initialFEN) {
    this.pieces = new ArrayList<>();
    this.movesHistory = new ArrayList<>();
    this.initialFEN = initialFEN;
  }

  public boolean canCastleKingSide(Color color) {
    if (isKingMoved(color)) return false;

    return movesHistory.stream()
      .filter(move -> move.getPieceToMove() instanceof Rook)
      .filter(move -> move.getPieceToMove().getColor().equals(color))
      .noneMatch(move -> move.getPieceToMove().getCoordinates().getFile().equals(BoardFile.FILE_H));
  }

  public boolean canCastleQueenSide(Color color) {
    if (isKingMoved(color)) return false;

    return movesHistory.stream()
      .filter(move -> move.getPieceToMove() instanceof Rook)
      .filter(move -> move.getPieceToMove().getColor().equals(color))
      .noneMatch(move -> move.getPieceToMove().getCoordinates().getFile().equals(BoardFile.FILE_A));
  }

  public boolean isKingMoved(Color color) {
    return movesHistory.stream()
      .filter(move -> move.getPieceToMove() instanceof King)
      .anyMatch(move -> move.getPieceToMove().getColor().equals(color));
  }

  public int getHalfMoveClock() {
    int halfMoveClock = 0;
    for (Move move : getMovesHistory()) {
      if (move.isCaptured() || move.getPieceToMove() instanceof Pawn) halfMoveClock = 0;
      else halfMoveClock++;
    }
    return halfMoveClock;
  }

  public int getFullMoveNumber() {
    return movesHistory.size() / 2 + 1;
  }

  public Optional<Coordinates> getEnPassantMove() {
    return pieces.stream()
      .filter(piece -> piece instanceof Pawn)
      .flatMap(piece -> piece.getAvailableAttackMoves(this).stream()
        .flatMap(coordinates -> ((Pawn) piece).getEnPassantMove(this, coordinates).stream()))
      .findFirst();
  }

  public Set<Coordinates> getAttackedSquaresByColor(Color color) {
    return pieces.stream()
      .filter(piece -> piece.getColor().equals(color))
      .flatMap(piece -> piece.getAvailableAttackMoves(this).stream())
      .collect(Collectors.toSet());
  }

  public boolean isSquareAttackedByColor(Color color, Coordinates coordinates) {
    return this.getAttackedSquaresByColor(color).contains(coordinates);
  }

  public List<Piece> getPiecesByColor(Color color) {
    return pieces.stream().filter(piece -> piece.getColor().equals(color)).collect(Collectors.toList());
  }

  public boolean isSquareEmpty(Coordinates coordinates) {
    return getPiece(coordinates).isEmpty();
  }

  public Optional<Piece> getPiece(Coordinates coordinates) {
    return pieces.stream()
      .filter(p -> p.getCoordinates().equals(coordinates))
      .findFirst();
  }

  public void setPiece(Piece piece, Coordinates coordinates) {
    piece.setCoordinates(coordinates);
    pieces.add(piece);
  }

  public void removePiece(Coordinates coordinates) {
    Optional<Piece> piece = pieces.stream()
      .filter(p -> p.getCoordinates().equals(coordinates))
      .findFirst();
    piece.ifPresent(p -> pieces.remove(p));
  }

  public void startPosition() {
    pieces.add(new Rook(new Coordinates(BoardFile.FILE_A, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Rook(new Coordinates(BoardFile.FILE_A, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Knight(new Coordinates(BoardFile.FILE_B, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Knight(new Coordinates(BoardFile.FILE_B, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Bishop(new Coordinates(BoardFile.FILE_C, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Bishop(new Coordinates(BoardFile.FILE_C, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Queen(new Coordinates(BoardFile.FILE_D, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Queen(new Coordinates(BoardFile.FILE_D, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new King(new Coordinates(BoardFile.FILE_E, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new King(new Coordinates(BoardFile.FILE_E, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Bishop(new Coordinates(BoardFile.FILE_F, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Bishop(new Coordinates(BoardFile.FILE_F, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Knight(new Coordinates(BoardFile.FILE_G, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Knight(new Coordinates(BoardFile.FILE_G, BoardRank.RANK_8), Color.BLACK));

    pieces.add(new Rook(new Coordinates(BoardFile.FILE_H, BoardRank.RANK_1), Color.WHITE));
    pieces.add(new Rook(new Coordinates(BoardFile.FILE_H, BoardRank.RANK_8), Color.BLACK));

    for (int i = 0; i <= 7; i++) {
      pieces.add(new Pawn(new Coordinates(BoardFile.fromNumber(i), BoardRank.RANK_2), Color.WHITE));
      pieces.add(new Pawn(new Coordinates(BoardFile.fromNumber(i), BoardRank.RANK_7), Color.BLACK));
    }
  }
}
