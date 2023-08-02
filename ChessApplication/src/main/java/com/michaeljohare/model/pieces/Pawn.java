package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class Pawn extends ChessPiece {
    public static final String BLACK_PAWN = "♟";
    public static final String WHITE_PAWN = "♙";
    public boolean canCaptureEnPassantRight;
    public boolean canCaptureEnPassantLeft;

    public Pawn(Square currentSquare, Player player) {
        super(currentSquare, player);
        this.canCaptureEnPassantRight = false;
        this.canCaptureEnPassantLeft = false;
    }

    public boolean isEnPassantVulnerable() {
        return Math.abs(currentSquare.getX() - previousSquares.peek().getX()) == 2;
    }

    @Override
    public List<Square> getMoves() {
        int x = currentSquare.getX(), y = currentSquare.getY();
        List<Square> availableMoves = new ArrayList<>();
        if (player.getPlayer().equals(PLAYER_1)) {
            if (x > 0 && isEmpty(x - 1, y)) {
                movePiece(new Square(x - 1, y));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x - 1, y));
                }
                undoMovePiece(EMPTY);
                if (x == 6 && isEmpty(x - 2, y)) {
                    movePiece(new Square(x - 2, y));
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x - 2, y));
                    }
                    undoMovePiece(EMPTY);
                }
            }
            if (x > 0 && y < 7 && !isEmpty(x - 1, y + 1) &&
                    !player.getPlayer().equals(board[x - 1][y + 1].substring(1))) {
                String piece = board[x - 1][y + 1];
                movePiece(new Square(x - 1, y + 1));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x - 1, y + 1));
                }
                undoMovePiece(piece);
            }
            if (x > 0 && y > 0 && !isEmpty(x - 1, y - 1) &&
                    !player.getPlayer().equals(board[x - 1][y - 1].substring(1))) {
                String piece = board[x - 1][y - 1];
                movePiece(new Square(x - 1, y - 1));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x - 1, y - 1));
                }
                undoMovePiece(piece);
            }

            // En passant Player1
            if (canCaptureEnPassantRight) {
                if (x > 0 && y < 7 && !isEmpty(x, y + 1) && isEmpty(x - 1, y + 1) && board[x][y + 1].equals(PAWN + PLAYER_2)) {
                    String piece = board[x][y + 1];
                    movePiece(new Square(x - 1, y + 1));
                    // was incorrect
                    board[x][y + 1] = EMPTY;
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x - 1, y + 1));
                    }
                    undoEnPassant(piece, 1);
                }
            }
            if (canCaptureEnPassantLeft) {
                if (x > 0 && y > 0 && !isEmpty(x, y - 1) && isEmpty(x - 1, y - 1) && board[x][y - 1].equals(PAWN + PLAYER_2)) {
                    String piece = board[x][y - 1];
                    movePiece(new Square(x - 1, y - 1));
                    board[x][y - 1] = EMPTY;
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x - 1, y - 1));
                    }
                    undoEnPassant(piece, 1);
                }
            }
        }

        if (player.getPlayer().equals(PLAYER_2)) {
            if (x < 7 && isEmpty(x + 1, y)) {
                movePiece(new Square(x + 1, y));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x + 1, y));
                }
                undoMovePiece(EMPTY);
                if (x == 1 && isEmpty(x + 2, y)) {
                    movePiece(new Square(x + 2, y));
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x + 2, y));
                    }
                    undoMovePiece(EMPTY);
                }
            }
            if (x < 7 && y < 7 && !isEmpty(x + 1, y + 1) &&
                    !player.getPlayer().equals(board[x + 1][y + 1].substring(1))) {
                String piece = board[x + 1][y + 1];
                movePiece(new Square(x + 1, y + 1));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x + 1, y + 1));
                }
                undoMovePiece(piece);
            }
            if (x < 7 && y > 0 && !isEmpty(x + 1, y - 1) &&
                    !player.getPlayer().equals(board[x + 1][y - 1].substring(1))) {
                String piece = board[x + 1][y - 1];
                movePiece(new Square(x + 1, y - 1));
                if (!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(x + 1, y - 1));
                }
                undoMovePiece(piece);
            }

            // En passant Player2
            if (canCaptureEnPassantRight) {
                if (x < 7 && y < 7 && !isEmpty(x, y + 1) && isEmpty(x + 1, y + 1) && board[x][y + 1].equals(PAWN + PLAYER_1)) {
                    String piece = board[x][y + 1];
                    movePiece(new Square(x + 1, y + 1));
                    board[x][y + 1] = EMPTY;
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x + 1, y + 1));
                    }
                    undoEnPassant(piece, -1);
                }
            }
            if (canCaptureEnPassantLeft) {
                if (x < 7 && y > 0 && !isEmpty(x, y - 1) && isEmpty(x + 1, y - 1) && board[x][y - 1].equals(PAWN + PLAYER_1)) {
                    String piece = board[x][y - 1];
                    movePiece(new Square(x + 1, y - 1));
                    board[x][y - 1] = EMPTY;
                    if (!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(x + 1, y - 1));
                    }
                    undoEnPassant(piece, -1);
                }
            }
        }
        return availableMoves;
    }

    public ChessPiece promoteTo(String pieceType) {
        ChessPiece promotedPiece;

        switch (pieceType) {
            case "Queen":
                promotedPiece = new Queen(currentSquare, player);
                break;
            case "Rook":
                promotedPiece = new Rook(currentSquare, player);
                break;
            case "Bishop":
                promotedPiece = new Bishop(currentSquare, player);
                break;
            case "Knight":
                promotedPiece = new Knight(currentSquare, player);
                break;
            default:
                throw new IllegalArgumentException("Invalid promotion choice: " + pieceType);
        }

        player.promotePawn(this, promotedPiece);
        return promotedPiece;
    }

    @Override
    public String getChessPieceConstant() {
        return PAWN;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_PAWN;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_PAWN;
    }
}
