package com.michaeljohare.view;

public interface ChessControllerListener {
    void handleSquareClick(int row, int col);
    void handlePlayAgainButtonClick();
    void handleUndoButtonClick();
}
