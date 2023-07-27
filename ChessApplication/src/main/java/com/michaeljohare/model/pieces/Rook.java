package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class Rook extends ChessPiece {
    public static final String BLACK_ROOK = "♜";
    public static final String WHITE_ROOK = "♖";
    public boolean hasMoved;

    public Rook(Square currentSquare, Player player) {
        super(currentSquare, player);
        hasMoved = false;
    }

    public boolean isAbleToCastle() {
        return !hasMoved;
    }

    @Override
    public List<Square> getMoves() {
        int x = currentSquare.getX(), y = currentSquare.getY();
        List<Square> availableMoves = new ArrayList<>();
        while (x < 7 && isEmpty(x + 1, y)) {
            movePiece(new Square(x + 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y));
            }
            undoMovePiece(EMPTY);
            x++;
        }

        if (x < 7 && !player.getPlayer().equals(board[x + 1][y].substring(1))) {
            String piece = board[x + 1][y];
            movePiece(new Square(x + 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y));
            }
            undoMovePiece(piece);
        }

        x = currentSquare.getX();

        while (x > 0 && isEmpty(x - 1, y)) {
            movePiece(new Square(x - 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y));
            }
            undoMovePiece(EMPTY);
            x--;
        }

        if (x > 0 && !player.getPlayer().equals(board[x - 1][y].substring(1))) {
            String piece = board[x - 1][y];
            movePiece(new Square(x - 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y));
            }
            undoMovePiece(piece);
        }

        x = currentSquare.getX();

        while (y < 7 && isEmpty(x, y + 1)) {
            movePiece(new Square(x, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y + 1));
            }
            undoMovePiece(EMPTY);
            y++;
        }

        if (y < 7 && !player.getPlayer().equals(board[x][y + 1].substring(1))) {
            String piece = board[x][y + 1];
            movePiece(new Square(x, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y + 1));
            }
            undoMovePiece(piece);
        }

        y = currentSquare.getY();

        while (y > 0 && isEmpty(x, y - 1)) {
            movePiece(new Square(x, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y - 1));
            }
            undoMovePiece(EMPTY);
            y--;
        }

        if (y > 0 && !player.getPlayer().equals(board[x][y - 1].substring(1))) {
            String piece = board[x][y - 1];
            movePiece(new Square(x, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y - 1));
            }
            undoMovePiece(piece);
        }

        return availableMoves;
    }

    @Override
    public String getChessPieceConstant() {
        return ROOK;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_ROOK;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_ROOK;
    }
}
