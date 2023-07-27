package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class Knight extends ChessPiece {
    public static final String BLACK_KNIGHT = "♞";
    public static final String WHITE_KNIGHT = "♘";

    public Knight(Square currentSquare, Player player) {
        super(currentSquare, player);
    }

    @Override
    public List<Square> getMoves() {
        List<Square> availableMoves = new ArrayList<>();
        int x = currentSquare.getX(), y = currentSquare.getY();
        if (x < 7 && y < 6 && isEmpty(x + 1, y + 2)) {
            movePiece(new Square(x + 1, y + 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 2));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 0 && y < 6 && isEmpty(x - 1, y + 2)) {
            movePiece(new Square(x - 1, y + 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 2));
            }
            undoMovePiece(EMPTY);
        }
        if (x < 6 && y > 0 && isEmpty(x + 2, y - 1)) {
            movePiece(new Square(x + 2, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 2, y - 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x < 6 && y < 7 && isEmpty(x + 2, y + 1)) {
            movePiece(new Square(x + 2, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 2, y + 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x < 7 && y > 1 && isEmpty(x + 1, y - 2)) {
            movePiece(new Square(x + 1, y - 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 2));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 0 && y > 1 && isEmpty(x - 1, y - 2)) {
            movePiece(new Square(x - 1, y - 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y - 2));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 1 && y > 0 && isEmpty(x - 2, y - 1)) {
            movePiece(new Square(x - 2, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 2, y - 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 1 && y < 7 && isEmpty(x - 2, y + 1)) {
            movePiece(new Square(x - 2, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 2, y + 1));
            }
            undoMovePiece(EMPTY);
        }

        if (x < 7 && y < 6 && !isEmpty(x + 1, y + 2) && !player.getPlayer().equals(board[x + 1][y + 2].substring(1))) {
            String piece = board[x + 1][y + 2];
            movePiece(new Square(x + 1, y + 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 2));
            }
            undoMovePiece(piece);
        }
        if (x > 0 && y < 6 && !isEmpty(x - 1, y + 2) &&
                !player.getPlayer().equals(board[x - 1][y + 2].substring(1))) {
            String piece = board[x - 1][y + 2];
            movePiece(new Square(x - 1, y + 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 2));
            }
            undoMovePiece(piece);
        }
        if (x < 6 && y > 0 && !isEmpty(x + 2, y - 1) &&
                !player.getPlayer().equals(board[x + 2][y - 1].substring(1))) {
            String piece = board[x + 2][y - 1];
            movePiece(new Square(x + 2, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 2, y - 1));
            }
            undoMovePiece(piece);
        }
        if (x < 6 && y < 7 && !isEmpty(x + 2, y + 1) &&
                !player.getPlayer().equals(board[x + 2][y + 1].substring(1))) {
            String piece = board[x + 2][y + 1];
            movePiece(new Square(x + 2, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 2, y + 1));
            }
            undoMovePiece(piece);
        }
        if (x < 7 && y > 1 && !isEmpty(x + 1, y - 2) &&
                !player.getPlayer().equals(board[x + 1][y - 2].substring(1))) {
            String piece = board[x + 1][y - 2];
            movePiece(new Square(x + 1, y - 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 2));
            }
            undoMovePiece(piece);
        }
        if (x > 0 && y > 1 && !isEmpty(x - 1, y - 2) &&
                !player.getPlayer().equals(board[x - 1][y - 2].substring(1))) {
            String piece = board[x - 1][y - 2];
            movePiece(new Square(x - 1, y - 2));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y - 2));
            }
            undoMovePiece(piece);
        }
        if (x > 1 && y > 0 && !isEmpty(x - 2, y - 1) &&
                !player.getPlayer().equals(board[x - 2][y - 1].substring(1))) {
            String piece = board[x - 2][y - 1];
            movePiece(new Square(x - 2, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 2, y - 1));
            }
            undoMovePiece(piece);
        }
        if (x > 1 && y < 7 && !isEmpty(x - 2, y + 1) &&
                !player.getPlayer().equals(board[x - 2][y + 1].substring(1))) {
            String piece = board[x - 2][y + 1];
            movePiece(new Square(x - 2, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 2, y + 1));
            }
            undoMovePiece(piece);
        }

        return availableMoves;
    }

    @Override
    public String getChessPieceConstant() {
        return KNIGHT;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_KNIGHT;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_KNIGHT;
    }
}
