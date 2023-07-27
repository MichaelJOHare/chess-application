package com.michaeljohare.model;

import java.util.List;

import static com.michaeljohare.model.Board.*;

public abstract class ChessPiece {
    protected Square lastSquare, currentSquare;
    protected Player player;
    private boolean isAlive;

    public ChessPiece(Square currentSquare, Player player) {
        this.lastSquare = null;
        this.currentSquare = currentSquare;
        this.player = player;
        isAlive = true;
    }

    public abstract List<Square> getMoves();
    public abstract String getChessPieceConstant();
    public abstract String getWhiteChessPieceSymbol();
    public abstract String getBlackChessPieceSymbol();

    public void movePiece(Square end) {
        board[end.getX()][end.getY()] = getChessPieceConstant() + player.getPlayer();
        board[currentSquare.getX()][currentSquare.getY()] = EMPTY;
        lastSquare = currentSquare;
        currentSquare = end;
    }
    public void undoMovePiece(String piece) {
        board[lastSquare.getX()][lastSquare.getY()] = getChessPieceConstant() + player.getPlayer();
        board[currentSquare.getX()][currentSquare.getY()] = piece;
        currentSquare = lastSquare;
        lastSquare = null;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y].equals(EMPTY);
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void capture() {
        isAlive = false;
    }
    public void undoCapture() {
        isAlive = true;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ChessPiece) {
            ChessPiece piece = (ChessPiece) o;
            return piece.lastSquare == this.lastSquare && piece.currentSquare == this.currentSquare;
        }
        return false;
    }
    @Override
    public String toString() {
        return getClass().getTypeName() + "at" + currentSquare;
    }
}
