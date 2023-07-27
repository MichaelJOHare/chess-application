package com.michaeljohare.view;

import com.michaeljohare.controller.ChessGUIListener;
import com.michaeljohare.model.ChessPiece;
import com.michaeljohare.model.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.michaeljohare.model.Board.*;

public class ChessGUI extends JFrame implements ChessGUIListener {
    private ChessControllerListener controller;
    private JButton[][] chessButtons;
    private JButton playAgainButton;
    private Color defaultButtonColor;
    private JTextArea logTextArea;
    private final List<ChessPiece> player1CapturedPieces = new ArrayList<>();
    private final List<ChessPiece> player2CapturedPieces = new ArrayList<>();
    private final List<Square> highlightedSquares = new ArrayList<>();
    private JScrollPane logScrollPane;
    private JTextArea player1CapturedArea;
    private JTextArea player2CapturedArea;

    public ChessGUI() {
        initializeGUI();
    }

    public void setController(ChessControllerListener controller) {
        this.controller = controller;
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Chess");
        updateFrame(frame);
        frame.setSize(1200, 1000);
        frame.setLayout(new BorderLayout());

        JPanel chessboardPanel = createChessboardPanel();

        logTextArea = createLogTextArea();
        logTextArea.setFont(new Font("Roboto", Font.PLAIN, 20));
        logTextArea.setText("\n\n\n Welcome to Michael's Chess Game! \n Use the undo button to undo a \n previous move. " +
                "\n\n It is White's turn to move first.");
        logScrollPane = new JScrollPane(logTextArea);

        player1CapturedArea = createCapturedArea();
        player2CapturedArea = createCapturedArea();
        updateCapturedPiecesDisplay();

        JPanel rightPanel = createRightPanel();

        frame.add(chessboardPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        updateGUI();
    }

    private JPanel createChessboardPanel() {
        JPanel chessboardPanel = new JPanel(new GridLayout(8, 8));
        chessButtons = new JButton[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                chessButtons[row][col] = new JButton();
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        chessButtons[row][col].setBackground(new Color(248, 240, 198));
                    } else {
                        chessButtons[row][col].setBackground(new Color(156, 98, 69));
                    }
                } else {
                    if (col % 2 == 1) {
                        chessButtons[row][col].setBackground(new Color(248, 240, 198));
                    } else {
                        chessButtons[row][col].setBackground(new Color(156, 98, 69));
                    }
                }
                updateButton(row, col);
                final int finalRow = row;
                final int finalCol = col;
                chessButtons[row][col].addActionListener(e -> onSquareClick(finalRow, finalCol));
                chessboardPanel.add(chessButtons[row][col]);
            }
        }
        return chessboardPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Roboto", Font.BOLD, 24));
        playAgainButton.addActionListener(e -> onPlayAgainButtonClick());
        defaultButtonColor = playAgainButton.getBackground();

        JButton undoButton = new JButton("Undo");
        undoButton.setFont(new Font("Roboto", Font.BOLD, 24));
        undoButton.addActionListener(e -> onUndoButtonClick());

        JPanel playAgainButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playAgainButtonPanel.add(playAgainButton);

        JPanel undoButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        undoButtonPanel.add(undoButton);

        JPanel logPanelWithButtons = new JPanel(new BorderLayout());
        logPanelWithButtons.add(logScrollPane, BorderLayout.CENTER);
        logPanelWithButtons.add(playAgainButtonPanel, BorderLayout.NORTH);
        logPanelWithButtons.add(undoButtonPanel, BorderLayout.SOUTH);

        rightPanel.add(player1CapturedArea, BorderLayout.SOUTH);
        rightPanel.add(logPanelWithButtons, BorderLayout.CENTER);
        rightPanel.add(player2CapturedArea, BorderLayout.NORTH);

        return rightPanel;
    }

    private JTextArea createLogTextArea() {
        JTextArea logTextArea = new JTextArea(5, 20);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setEditable(false);
        return logTextArea;
    }

    private JTextArea createCapturedArea() {
        JTextArea capturedArea = new JTextArea(15, 8);

        capturedArea.setEditable(false);
        capturedArea.setLayout(new FlowLayout());
        capturedArea.setLineWrap(true);
        capturedArea.setWrapStyleWord(true);

        return capturedArea;
    }

    @Override
    public int createPromotionPane(ChessPiece playerPiece) {
        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
        return JOptionPane.showOptionDialog(
                chessButtons[playerPiece.getCurrentSquare().getX()][playerPiece.getCurrentSquare().getY()],
                "Select a piece to promote the pawn to:",
                "Pawn Promotion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    @Override
    public void updateGUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                updateButton(row, col);
            }
        }
    }

    @Override
    public void setHighlightedSquares(List<Square> moves) {
        for (Square square : moves) {
            chessButtons[square.getX()][square.getY()].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
            highlightedSquares.add(square);
        }
    }

    @Override
    public void clearHighlightedSquares() {
        for (Square square : highlightedSquares) {
            chessButtons[square.getX()][square.getY()].setBorder(null);
        }
        highlightedSquares.clear();
    }

    private String getImagePath(String pieceType, String player) {
        return "/" + (player.equals(PLAYER_1) ? "White_" : "Black_") + pieceType + ".png";
    }

    private void updateFrame(JFrame frame) {
        try {
            Image frameIcon = ImageIO.read(ChessGUI.class.getResource("/Frame_Icon.png"));
            frame.setIconImage(frameIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateButton(int row, int col) {
        String pieceType = getPieceType(board[row][col]);
        String player = getPlayer(board[row][col]);

        chessButtons[row][col].setBorder(null);

        if (!pieceType.equals(EMPTY)) {
            String imagePath = getImagePath(pieceType, player);
            try {
                Image pieceImage = ImageIO.read(ChessGUI.class.getResource(imagePath));
                chessButtons[row][col].setIcon(new ImageIcon(pieceImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chessButtons[row][col].setIcon(null);
        }
    }

    @Override
    public void updatePlayAgainButton(Color backgroundColor, Color foregroundColor) {
        playAgainButton.setBackground(backgroundColor);
        playAgainButton.setForeground(foregroundColor);
    }

    @Override
    public void updateLogTextArea(String string) {
        logTextArea.setText(string);
    }

    @Override
    public void addRemoveCapture1Area(ChessPiece capturedPiece, boolean trueForAdd) {
        if (trueForAdd) {
            player1CapturedPieces.add(capturedPiece);
        } else {
            player1CapturedPieces.remove(capturedPiece);
        }
    }

    @Override
    public void addRemoveCapture2Area(ChessPiece capturedPiece, boolean trueForAdd) {
        if (trueForAdd) {
            player2CapturedPieces.add(capturedPiece);
        } else {
            player2CapturedPieces.remove(capturedPiece);
        }
    }

    @Override
    public void updateCapturedPiecesDisplay() {
        player1CapturedArea.removeAll();
        player2CapturedArea.removeAll();

        Font capturedPieceFont = new Font("Roboto", Font.PLAIN, 26);
        Font capturedPiecesTitleFont = new Font("Roboto", Font.BOLD, 24);

        Border paddingBorder = BorderFactory.createEmptyBorder(5, 70, 5, 70);
        Border lineBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);

        JLabel capturedPiecesTitle1 = new JLabel("Captured Pieces");
        capturedPiecesTitle1.setFont(capturedPiecesTitleFont);
        capturedPiecesTitle1.setBorder(compoundBorder);
        JLabel capturedPiecesTitle2 = new JLabel("Captured Pieces");
        capturedPiecesTitle2.setFont(capturedPiecesTitleFont);
        capturedPiecesTitle2.setBorder(compoundBorder);
        player1CapturedArea.add(capturedPiecesTitle1);
        player2CapturedArea.add(capturedPiecesTitle2);

        for (ChessPiece piece : player1CapturedPieces) {
            JLabel blackCapturedPieceLabel = new JLabel(piece.getBlackChessPieceSymbol());
            blackCapturedPieceLabel.setFont(capturedPieceFont);
            player1CapturedArea.add(blackCapturedPieceLabel);
        }

        for (ChessPiece piece : player2CapturedPieces) {
            JLabel whiteCapturedPieceLabel = new JLabel(piece.getWhiteChessPieceSymbol());
            whiteCapturedPieceLabel.setFont(capturedPieceFont);
            player2CapturedArea.add(whiteCapturedPieceLabel);
        }

        player1CapturedArea.revalidate();
        player1CapturedArea.repaint();
        player2CapturedArea.revalidate();
        player2CapturedArea.repaint();
    }


    private String getPieceType(String square) {
        if (square.startsWith(PAWN)) {
            return "Pawn";
        } else if (square.startsWith(ROOK)) {
            return "Rook";
        } else if (square.startsWith(BISHOP)) {
            return "Bishop";
        } else if (square.startsWith(KNIGHT)) {
            return "Knight";
        } else if (square.startsWith(KING)) {
            return "King";
        } else if (square.startsWith(QUEEN)) {
            return "Queen";
        } else {
            return EMPTY;
        }
    }

    private String getPlayer(String square) {
        if (square.endsWith(PLAYER_1)) {
            return PLAYER_1;
        } else if (square.endsWith(PLAYER_2)) {
            return PLAYER_2;
        } else {
            return EMPTY;
        }
    }

    private void onSquareClick(int row, int col) {
        controller.handleSquareClick(row, col);
    }

    private void onPlayAgainButtonClick() {
        controller.handlePlayAgainButtonClick();
        player1CapturedPieces.clear();
        player2CapturedPieces.clear();
        playAgainButton.setBackground(defaultButtonColor);
        playAgainButton.setForeground(null);
        highlightedSquares.clear();
        updateCapturedPiecesDisplay();
        updateGUI();
    }

    private void onUndoButtonClick() {
        controller.handleUndoButtonClick();
    }
}
