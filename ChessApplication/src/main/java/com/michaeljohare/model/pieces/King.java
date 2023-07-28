package com.michaeljohare.model.pieces;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;

import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class King extends ChessPiece {
    public static final String WHITE_KING = "♔";
    public static final String BLACK_KING = "♚";

    public boolean hasMoved;

    public King(Square currentSquare, Player player) {
        super(currentSquare, player);
        hasMoved = false;
    }


    public boolean isInCheck() {
        int x = currentSquare.getX(), y = currentSquare.getY();



        // Diagonal (Queen & Bishop) Checker
        while (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            x++;
            y++;
        }

        if ((x < 7 && y < 7 && !player.getPlayer().equals(board[x + 1][y + 1].substring(1)))
                && (board[x + 1][y + 1].startsWith(QUEEN) || board[x + 1][y + 1].startsWith(BISHOP))) {
            return true;
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            x++;
            y--;
        }

        if ((x < 7 && y > 0 && !player.getPlayer().equals(board[x + 1][y - 1].substring(1)))
                && (board[x + 1][y - 1].startsWith(QUEEN) || board[x + 1][y - 1].startsWith(BISHOP))) {
            return true;
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            x--;
            y--;
        }

        if ((x > 0 && y > 0 && !player.getPlayer().equals(board[x - 1][y - 1].substring(1)))
                && (board[x - 1][y - 1].startsWith(QUEEN) || board[x - 1][y - 1].startsWith(BISHOP))) {
            return true;
        }

        x = currentSquare.getX();
        y = currentSquare.getY();

        while (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            x--;
            y++;
        }

        if ((x > 0 && y < 7 && !player.getPlayer().equals(board[x - 1][y + 1].substring(1)))
                && (board[x - 1][y + 1].startsWith(QUEEN) || board[x - 1][y + 1].startsWith(BISHOP))) {
            return true;
        }

        x = currentSquare.getX();
        y = currentSquare.getY();



        // Straight line (Queen & Rook) Checker
        while (x < 7 && isEmpty(x + 1, y)) {
            x++;
        }

        if ((x < 7 && !player.getPlayer().equals(board[x + 1][y].substring(1)))
                && (board[x + 1][y].startsWith(QUEEN) || board[x + 1][y].startsWith(ROOK))) {
            return true;
        }

        x = currentSquare.getX();

        while (x > 0 && isEmpty(x - 1, y)) {
            x--;
        }

        if ((x > 0 && !player.getPlayer().equals(board[x - 1][y].substring(1)))
                && (board[x - 1][y].startsWith(QUEEN) || board[x - 1][y].startsWith(ROOK))) {
            return true;
        }

        x = currentSquare.getX();

        while (y < 7 && isEmpty(x, y + 1)) {
            y++;
        }

        if ((y < 7 && !player.getPlayer().equals(board[x][y + 1].substring(1)))
                && (board[x][y + 1].startsWith(QUEEN) || board[x][y + 1].startsWith(ROOK))) {
            return true;
        }

        y = currentSquare.getY();

        while (y > 0 && isEmpty(x, y - 1)) {
            y--;
        }

        if ((y > 0 && !player.getPlayer().equals(board[x][y - 1].substring(1)))
                && (board[x][y - 1].startsWith(QUEEN) || board[x][y - 1].startsWith(ROOK))) {
            return true;
        }

        x = currentSquare.getX();
        y = currentSquare.getY();



        // 1 square padded box (Opponent King) Checker
        if ((x < 7 && y < 7 && !player.getPlayer().equals(board[x + 1][y + 1].substring(1)))
                && (board[x + 1][y + 1].startsWith(KING))) {
            return true;
        }
        if ((x > 0 && y < 7 && !player.getPlayer().equals(board[x - 1][y + 1].substring(1)))
                && (board[x - 1][y + 1].startsWith(KING))) {
            return true;
        }
        if ((x < 7 && y > 0 && !player.getPlayer().equals(board[x + 1][y - 1].substring(1)))
                && (board[x + 1][y - 1].startsWith(KING))) {
            return true;
        }
        if ((x > 0 && y > 0 && !player.getPlayer().equals(board[x - 1][y - 1].substring(1)))
                && (board[x - 1][y - 1].startsWith(KING))) {
            return true;
        }
        if ((x < 7 && !player.getPlayer().equals(board[x + 1][y].substring(1)))
                && (board[x + 1][y].startsWith(KING)))  {
            return true;
        }
        if ((x > 0 && !player.getPlayer().equals(board[x - 1][y].substring(1)))
                && (board[x - 1][y].startsWith(KING))) {
            return true;
        }
        if ((y < 7 && !player.getPlayer().equals(board[x][y + 1].substring(1)))
                && (board[x][y + 1].startsWith(KING))) {
            return true;
        }
        if ((y > 0 && !player.getPlayer().equals(board[x][y - 1].substring(1)))
                && (board[x][y - 1].startsWith(KING))) {
            return true;
        }



        // L shape (Knight) Checker
        if ((x < 7 && y < 6 && !player.getPlayer().equals(board[x + 1][y + 2].substring(1)))
                && (board[x + 1][y + 2].startsWith(KNIGHT))) {
            return true;
        }
        if ((x > 0 && y < 6 && !player.getPlayer().equals(board[x - 1][y + 2].substring(1)))
                && (board[x - 1][y + 2].startsWith(KNIGHT))) {
            return true;
        }
        if ((x < 6 && y > 0 && !player.getPlayer().equals(board[x + 2][y - 1].substring(1)))
                && (board[x + 2][y - 1].startsWith(KNIGHT))) {
            return true;
        }
        if ((x < 6 && y < 7 && !player.getPlayer().equals(board[x + 2][y + 1].substring(1)))
                && (board[x + 2][y + 1].startsWith(KNIGHT))) {
            return true;
        }
        if ((x < 7 && y > 1 && !player.getPlayer().equals(board[x + 1][y - 2].substring(1)))
                && (board[x + 1][y - 2].startsWith(KNIGHT))) {
            return true;
        }
        if ((x > 0 && y > 1 && !player.getPlayer().equals(board[x - 1][y - 2].substring(1)))
                && (board[x - 1][y - 2].startsWith(KNIGHT))) {
            return true;
        }
        if ((x > 1 && y > 0 && !player.getPlayer().equals(board[x - 2][y - 1].substring(1)))
                && (board[x - 2][y - 1].startsWith(KNIGHT))) {
            return true;
        }
        if ((x > 1 && y < 7 && !player.getPlayer().equals(board[x - 2][y + 1].substring(1)))
                && (board[x - 2][y + 1].startsWith(KNIGHT))) {
            return true;
        }



        // Directly diagonal (Pawn) Checker
        if (player.getPlayer().equals(PLAYER_1)) {
            if ((x > 0 && y > 0 && !player.getPlayer().equals(board[x - 1][y - 1].substring(1)))
                    && (board[x - 1][y - 1].startsWith(PAWN))) {
                return true;
            }
            return ((x > 0 && y < 7 && !player.getPlayer().equals(board[x - 1][y + 1].substring(1)))
                    && (board[x - 1][y + 1].startsWith(PAWN)));
        } else {
            if ((x < 7 && y > 0 && !player.getPlayer().equals(board[x + 1][y - 1].substring(1)))
                    && (board[x + 1][y - 1].startsWith(PAWN))) {
                return true;
            }
            return ((x < 7 && y < 7 && !player.getPlayer().equals(board[x + 1][y + 1].substring(1)))
                    && (board[x + 1][y + 1].startsWith(PAWN)));
        }

    }

    @Override
    public List<Square> getMoves() {
        int x = currentSquare.getX(), y = currentSquare.getY();
        List<Square> availableMoves = new ArrayList<>();
        if (x < 7 && isEmpty(x + 1, y)) {
            movePiece(new Square(x + 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 0 && isEmpty(x - 1, y)) {
            movePiece(new Square(x - 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y));
            }
            undoMovePiece(EMPTY);
        }

        // Castling rights + do or not do castling
        if (y < 7 && isEmpty(x, y + 1)) {
            movePiece(new Square(x, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y + 1));
            }
            undoMovePiece(EMPTY);

            if(player.getPlayer().equals(PLAYER_1)) {
                if (!player.getKing().isInCheck() && y < 6 && isEmpty(x, y+2) &&
                        currentSquare.equals(new Square(7, 4)) && !hasMoved && board[7][7].equals(ROOK + PLAYER_1)) {
                    Rook rook = (Rook)player.getPlayerPiece(new Square(7,7));
                    if(rook.isAbleToCastle()) {
                        movePiece(new Square(7, 6));
                        rook.movePiece(new Square(7,5));
                    }
                    if(!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(7,6));
                    }
                    undoMovePiece(EMPTY);
                    rook.undoMovePiece(EMPTY);
                }
            } else if(!player.getKing().isInCheck() && y < 6 && isEmpty(x, y+2) &&
                    currentSquare.equals(new Square(0, 4)) && !hasMoved && board[0][7].equals(ROOK + PLAYER_2)) {
                Rook rook = (Rook)player.getPlayerPiece(new Square(0,7));
                if(rook.isAbleToCastle()) {
                    movePiece(new Square(0, 6));
                    rook.movePiece(new Square(0,5));
                }
                if(!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(0,6));
                }
                undoMovePiece(EMPTY);
                rook.undoMovePiece(EMPTY);
            }
        }

        if (y > 0 && isEmpty(x, y - 1)) {
            movePiece(new Square(x, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y - 1));
            }
            undoMovePiece(EMPTY);

            if(player.getPlayer().equals(PLAYER_1)) {
                if (!player.getKing().isInCheck() && y>1 &&isEmpty(x, y-2) &&
                        currentSquare.equals(new Square(7, 4)) && !hasMoved && board[7][0].equals(ROOK + PLAYER_1)) {
                    Rook rook = (Rook)player.getPlayerPiece(new Square(7,0));
                    if(rook.isAbleToCastle()) {
                        movePiece(new Square(7, 2));
                        rook.movePiece(new Square(7,3));
                    }
                    if(!player.getKing().isInCheck()) {
                        availableMoves.add(new Square(7,2));
                    }
                    undoMovePiece(EMPTY);
                    rook.undoMovePiece(EMPTY);
                }
            } else if(!player.getKing().isInCheck() && y>1 && isEmpty(x, y-2) &&
                    currentSquare.equals(new Square(0, 4)) && !hasMoved && board[0][0].equals(ROOK + PLAYER_2)) {
                Rook rook = (Rook)player.getPlayerPiece(new Square(0,0));
                if(rook.isAbleToCastle()) {
                    movePiece(new Square(0, 2));
                    rook.movePiece(new Square(0,3));
                }
                if(!player.getKing().isInCheck()) {
                    availableMoves.add(new Square(0,2));
                }
                undoMovePiece(EMPTY);
                rook.undoMovePiece(EMPTY);
            }
        }

        // King moves
        if (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            movePiece(new Square(x + 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            movePiece(new Square(x + 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            movePiece(new Square(x - 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 1));
            }
            undoMovePiece(EMPTY);
        }
        if (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            movePiece(new Square(x - 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y - 1));
            }
            undoMovePiece(EMPTY);
        }

        if (x < 7 && !isEmpty(x + 1, y) && !player.getPlayer().equals(board[x + 1][y].substring(1))) {
            String piece = board[x + 1][y];
            movePiece(new Square(x + 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y));
            }
            undoMovePiece(piece);
        }
        if (x > 0 && !isEmpty(x - 1, y) && !player.getPlayer().equals(board[x - 1][y].substring(1))) {
            String piece = board[x - 1][y];
            movePiece(new Square(x - 1, y));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y));
            }
            undoMovePiece(piece);
        }
        if (y < 7 && !isEmpty(x, y + 1) && !player.getPlayer().equals(board[x][y + 1].substring(1))) {
            String piece = board[x][y + 1];
            movePiece(new Square(x, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y + 1));
            }
            undoMovePiece(piece);
        }
        if (y > 0 && !isEmpty(x, y - 1) && !player.getPlayer().equals(board[x][y - 1].substring(1))) {
            String piece = board[x][y - 1];
            movePiece(new Square(x, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x, y - 1));
            }
            undoMovePiece(piece);
        }
        if (x < 7 && y < 7 && !isEmpty(x + 1, y + 1) && !player.getPlayer().equals(board[x + 1][y + 1].substring(1))) {
            String piece = board[x + 1][y + 1];
            movePiece(new Square(x + 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y + 1));
            }
            undoMovePiece(piece);
        }
        if (x < 7 && y > 0 && !isEmpty(x + 1, y - 1) && !player.getPlayer().equals(board[x + 1][y - 1].substring(1))) {
            String piece = board[x + 1][y - 1];
            movePiece(new Square(x + 1, y - 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x + 1, y - 1));
            }
            undoMovePiece(piece);
        }
        if (x > 0 && y < 7 && !isEmpty(x - 1, y + 1) && !player.getPlayer().equals(board[x - 1][y + 1].substring(1))) {
            String piece = board[x - 1][y + 1];
            movePiece(new Square(x - 1, y + 1));
            if (!player.getKing().isInCheck()) {
                availableMoves.add(new Square(x - 1, y + 1));
            }
            undoMovePiece(piece);
        }
        if (x > 0 && y > 0 && !isEmpty(x - 1, y - 1) && !player.getPlayer().equals(board[x - 1][y - 1].substring(1))) {
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
        return KING;
    }

    @Override
    public String getWhiteChessPieceSymbol() {
        return WHITE_KING;
    }

    @Override
    public String getBlackChessPieceSymbol() {
        return BLACK_KING;
    }
}
