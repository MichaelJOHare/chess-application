package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class Bishop extends ChessPiece {
    public static final String BLACK_BISHOP = "♝";
    public static final String WHITE_BISHOP = "♗";

    public Bishop(Square currentSquare, Player player) {
        super(currentSquare, player);
    }

    @Override
    public List<Square> getMoves() {
        List<Square> availableMoves = new ArrayList<>();
        int x = currentSquare.getX(), y = currentSquare.getY();
        while (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            movePiece(new Square(x + 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 1));
            }
            undoMovePiece(EMPTY);
            x++;
            y++;
        }

        if (x < 7 && y < 7 && !player.getPlayer().equals(board[x + 1][y + 1].substring(1))) {
            String piece = board[x + 1][y + 1];
            movePiece(new Square(x + 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 1));
            }
            undoMovePiece(piece);
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            movePiece(new Square(x + 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 1));
            }
            undoMovePiece(EMPTY);
            x++;
            y--;
        }

        if (x < 7 && y > 0 && !player.getPlayer().equals(board[x + 1][y - 1].substring(1))) {
            String piece = board[x + 1][y - 1];
            movePiece(new Square(x + 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 1));
            }
            undoMovePiece(piece);
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            movePiece(new Square(x - 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 1));
            }
            undoMovePiece(EMPTY);
            x--;
            y++;
        }

        if (x > 0 && y < 7 && !player.getPlayer().equals(board[x - 1][y + 1].substring(1))) {
            String piece = board[x - 1][y + 1];
            movePiece(new Square(x - 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 1));
            }
            undoMovePiece(piece);
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            movePiece(new Square(x - 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y - 1));
            }
            undoMovePiece(EMPTY);
            x--;
            y--;
        }

        if (x > 0 && y > 0 && !player.getPlayer().equals(board[x - 1][y - 1].substring(1))) {
            String piece = board[x - 1][y - 1];
            movePiece(new Square(x - 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y - 1));
            }
            undoMovePiece(piece);
        }

        return availableMoves;
    }

    @Override
    public String getChessPieceConstant() {
        return BISHOP;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_BISHOP;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_BISHOP;
    }
}
