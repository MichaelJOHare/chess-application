package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class Queen extends ChessPiece {
    public static final String BLACK_QUEEN = "♛";
    public static final String WHITE_QUEEN = "♕";

    public Queen(Square currentSquare, Player player) {
        super(currentSquare, player);
    }

    @Override
    public List<Square> getMoves() {
        List<Square> availableMoves = new ArrayList<>();
        List<Square> rook = getRookMoves();
        List<Square> bishop = getBishopMoves();
        availableMoves.addAll(rook);
        availableMoves.addAll(bishop);
        return availableMoves;
    }

    private List<Square> getBishopMoves() {
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

    private List<Square> getRookMoves() {
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
        return QUEEN;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_QUEEN;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_QUEEN;
    }
}
