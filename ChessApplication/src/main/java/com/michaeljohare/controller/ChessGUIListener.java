package com.michaeljohare.controller;

import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Square;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface ChessGUIListener {
    void updateLogTextArea(String string);
    void updatePlayAgainButton(Color backGroundColor, Color foregroundColor);
    int createPromotionPane(ChessPiece playerPiece);
    void clearHighlightedSquares();
    void updateGUI();
    void setHighlightedSquares(List<Square> moves);
    void addRemoveCapture1Area(ChessPiece capturedPiece, boolean trueForAdd);
    void addRemoveCapture2Area(ChessPiece capturedPiece, boolean trueForAdd);
    void updateCapturedPiecesDisplay();
}
