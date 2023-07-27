package com.michaeljohare.controller;

import com.michaeljohare.model.Board;
import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Player;
import com.michaeljohare.model.Square;
import com.michaeljohare.model.pieces.*;
import com.michaeljohare.view.ChessControllerListener;

import java.awt.*;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class ChessController implements ChessControllerListener {
    private ChessGUIListener gui;
    private final Player player1;
    private final Player player2;
    private int turnCounter;
    private boolean player1HasCastled;
    private boolean player2HasCastled;
    private boolean previousMoveWasCastle;
    private boolean isFirstClick;
    private boolean pawnPromotionFlag;
    private ChessPiece playerPiece;
    private ChessPiece capturedPiece;
    private ChessPiece previousPiece;
    private final String lineBreaks = "\n\n\n\n\n";

    public ChessController() {
        player1 = new Player(Board.PLAYER_1);
        player2 = new Player(Board.PLAYER_2);
        turnCounter = 0;
        player1HasCastled = false;
        player2HasCastled = false;
        previousMoveWasCastle = false;
        isFirstClick = true;
        pawnPromotionFlag = false;
    }

    public void setGUI(ChessGUIListener gui) {
        this.gui = gui;
    }

    @Override
    public void handleSquareClick(int row, int col) {
        if (isFirstClick) {
            handleFirstClick(row, col);
            return;
        }
        Square targetSquare = new Square(row, col);
        if (playerPiece.getMoves().contains(targetSquare)) {
            previousMoveWasCastle = false;
            if (!isEmpty(row, col)) {
                handleSecondClickCapture(row, col);
            } else {
                handleSecondClickNonCaptureOrCastle(row, col);
            }
            handleLegalMove(row);
        } else {
            handleIllegalMove();
        }
    }

    @Override
    public void handlePlayAgainButtonClick() {
        Board.initializeBoard();
        player1.resetPlayer(PLAYER_1);
        player2.resetPlayer(PLAYER_2);
        turnCounter = 0;
        previousMoveWasCastle = false;
        player1HasCastled = false;
        player2HasCastled = false;
        isFirstClick = true;
        capturedPiece = null;
        playerPiece = null;
        previousPiece = null;
        gui.updateLogTextArea(lineBreaks + " Welcome to Michael's Chess Game! \n Use the undo button to undo a \n previous move. " +
                "\n\n It is White's turn to move first.");
    }

    @Override
    public void handleUndoButtonClick() {
        boolean wasUndoSuccessful = false;
        if (turnCounter > 0) {
            try {
                if (capturedPiece != null) {
                    handleUndoCapture();
                } else {
                    handleUndoNonCaptureOrCastle();
                }
                wasUndoSuccessful = true;
            } catch (Exception e) {
                gui.updateLogTextArea(lineBreaks + " You can only undo a previous move \n one time!");
                if (previousMoveWasCastle && turnCounter % 2 == 0) {
                    player1HasCastled = false;
                }
                if (previousMoveWasCastle && turnCounter % 2 == 1) {
                    player2HasCastled = false;
                }
            }
            if (wasUndoSuccessful) {
                handleLegalUndo();
            }
        }
    }

    private void handleFirstClick(int row, int col) {
        if (turnCounter % 2 == 0) {
            playerPiece = player1.getPlayerPiece(new Square(row, col));
        } else {
            playerPiece = player2.getPlayerPiece(new Square(row, col));
        }

        if (playerPiece == null) {
            gui.updateLogTextArea(lineBreaks + " There's no piece available in the\n selected square, or the " +
                    "piece you\n selected is not your piece.");
            return;
        }

        List<Square> moves = playerPiece.getMoves();
        if (moves.size() == 0) {
            gui.updateLogTextArea(lineBreaks + " The piece you selected does not\n have any legal moves");
            playerPiece = null;
            isFirstClick = true;
            return;
        }

        gui.setHighlightedSquares(moves);
        isFirstClick = false;
    }

    private void handleSecondClickCapture(int row, int col) {
        playerPiece.movePiece(new Square(row, col));

        if (turnCounter % 2 == 0) {
            capturedPiece = player2.getPlayerPiece(new Square(row, col));
            player2.capturePiece(capturedPiece);
            gui.addRemoveCapture1Area(capturedPiece, true);
            gui.updateCapturedPiecesDisplay();
        } else {
            capturedPiece = player1.getPlayerPiece(new Square(row, col));
            player1.capturePiece(capturedPiece);
            gui.addRemoveCapture2Area(capturedPiece, true);
            gui.updateCapturedPiecesDisplay();
        }

        if (playerPiece instanceof Pawn && playerPiece.getCurrentSquare().getX() == 0 ||
                playerPiece.getCurrentSquare().getX() == 7) {
            pawnPromotionFlag = true;
        }
    }

    private void handleSecondClickNonCaptureOrCastle(int row, int col) {
        capturedPiece = null;
        playerPiece.movePiece(new Square(row, col));

        if (playerPiece instanceof King) {
            if (turnCounter % 2 == 0 && !player1HasCastled && row == 7 && col == 6) {
                player1.getPlayerPiece(new Square(7, 7)).movePiece(new Square(7, 5));
                ((Rook) player1.getPlayerPiece(new Square(7, 5))).hasMoved = true;
                player1HasCastled = true;
                previousMoveWasCastle = true;
            }
            if (turnCounter % 2 == 0 && !player1HasCastled && row == 7 && col == 2) {
                player1.getPlayerPiece(new Square(7, 0)).movePiece(new Square(7, 3));
                ((Rook) player1.getPlayerPiece(new Square(7, 3))).hasMoved = true;
                player1HasCastled = true;
                previousMoveWasCastle = true;
            }
            if (turnCounter % 2 == 1  && !player2HasCastled && row == 0 && col == 6) {
                player2.getPlayerPiece(new Square(0, 7)).movePiece(new Square(0, 5));
                ((Rook) player2.getPlayerPiece(new Square(0, 5))).hasMoved = true;
                player2HasCastled = true;
                previousMoveWasCastle = true;
            }
            if (turnCounter % 2 == 1  && !player2HasCastled && row == 0 && col == 2) {
                player2.getPlayerPiece(new Square(0, 0)).movePiece(new Square(0, 3));
                ((Rook) player2.getPlayerPiece(new Square(0, 3))).hasMoved = true;
                player2HasCastled = true;
                previousMoveWasCastle = true;
            }
            ((King) playerPiece).hasMoved = true;
        }

        if (playerPiece instanceof Rook) ((Rook) playerPiece).hasMoved = true;
        if (playerPiece instanceof Pawn && playerPiece.getCurrentSquare().getX() == 0 ||
                playerPiece.getCurrentSquare().getX() == 7) {
            pawnPromotionFlag = true;
        }
    }

    private void handleLegalMove(int row) {

        handlePawnPromotion(row);

        gui.updateGUI();
        gui.clearHighlightedSquares();
        previousPiece = playerPiece;
        playerPiece = null;
        isFirstClick = true;
        turnCounter++;

        if (turnCounter % 2 == 0) {
            gui.updateLogTextArea(lineBreaks + " It's the white player's turn to move.");
        } else if (turnCounter % 2 == 1) {
            gui.updateLogTextArea(lineBreaks+ " It's the black player's turn to move.");
        }

        handleCheckmate();
    }

    private void handleIllegalMove() {
        gui.updateLogTextArea(lineBreaks + " The square you chose to move to is\n not a legal move, choose a " +
                "piece\n and try again.");
        gui.clearHighlightedSquares();
        playerPiece = null;
        isFirstClick = true;
    }

    private void handlePawnPromotion(int row) {

        if (pawnPromotionFlag) {
            if (playerPiece instanceof Pawn && (row == 0 || row == 7)) {
                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                int choice = gui.createPromotionPane(playerPiece);
                if (choice >= 0 && choice < options.length) {
                    String selectedPiece = options[choice];
                    ((Pawn) playerPiece).promoteTo(selectedPiece);
                }
            }
        }

        pawnPromotionFlag = false;
    }

    private void handleCheckmate() {

        if ((turnCounter % 2 == 0 && player1.getKing().isInCheck())
                || (turnCounter % 2 == 1 && player2.getKing().isInCheck())) {
            gui.updateLogTextArea(lineBreaks + " Check!");
        }

        if ((turnCounter % 2 == 0 && player1.getMoves().size() == 0) ||
                (turnCounter % 2 == 1 && player2.getMoves().size() == 0)) {
            if (turnCounter % 2 == 0) {
                gui.updateLogTextArea(lineBreaks + "     Player 2 has won the game!\n\n\tPlay again?");
            } else {
                gui.updateLogTextArea(lineBreaks + "     Player 1 has won the game!\n\n\tPlay again?");
            }
            gui.updatePlayAgainButton(Color.GREEN, Color.BLACK);
        }
    }

    private void handleUndoCapture() {
        if (turnCounter % 2 == 0) {
            previousPiece.undoMovePiece(capturedPiece.getChessPieceConstant() + player1.getPlayer());
            player1.undoCapturePiece(capturedPiece);
            gui.addRemoveCapture2Area(capturedPiece, false);
        } else {
            previousPiece.undoMovePiece(capturedPiece.getChessPieceConstant() + player2.getPlayer());
            player2.undoCapturePiece(capturedPiece);
            gui.addRemoveCapture1Area(capturedPiece, false);
        }
    }

    private void handleUndoNonCaptureOrCastle() {
        if (previousPiece instanceof King) ((King) previousPiece).hasMoved = false;
        if (previousPiece instanceof Rook) ((Rook) previousPiece).hasMoved = false;

        if (turnCounter % 2 == 1 && previousMoveWasCastle) {
            if (board[7][5].equals(ROOK + PLAYER_1)) {
                ((Rook) player1.getPlayerPiece(new Square(7, 5))).hasMoved = false;
                player1.getPlayerPiece(new Square(7, 5)).undoMovePiece(EMPTY);
                player1HasCastled = false;
            } else if (board[7][3].equals(ROOK + PLAYER_1)) {
                ((Rook) player1.getPlayerPiece(new Square(7, 3))).hasMoved = false;
                player1.getPlayerPiece(new Square(7, 3)).undoMovePiece(EMPTY);
                player1HasCastled = false;
            }
        } else if (turnCounter % 2 == 0 && previousMoveWasCastle){
            if (board[0][5].equals(ROOK + PLAYER_2)) {
                ((Rook) player2.getPlayerPiece(new Square(0, 5))).hasMoved = false;
                player2.getPlayerPiece(new Square(0, 5)).undoMovePiece(EMPTY);
                player2HasCastled = false;
            } else if (board[0][3].equals(ROOK + PLAYER_2)) {
                ((Rook) player2.getPlayerPiece(new Square(0, 3))).hasMoved = false;
                player2.getPlayerPiece(new Square(0, 3)).undoMovePiece(EMPTY);
                player2HasCastled = false;
            }
        }
        previousPiece.undoMovePiece(EMPTY);
    }

    private void handleLegalUndo() {
        gui.updateCapturedPiecesDisplay();
        gui.updateGUI();
        turnCounter--;
        playerPiece = null;
        isFirstClick = true;
        if (turnCounter % 2 == 0) {
            gui.updateLogTextArea(lineBreaks + " It's the white player's turn to move.");
        } else if (turnCounter % 2 == 1) {
            gui.updateLogTextArea(lineBreaks+ " It's the black player's turn to move.");
        }
    }

    private boolean isEmpty(int x, int y) {
        return board[x][y].equals(EMPTY);
    }
}
