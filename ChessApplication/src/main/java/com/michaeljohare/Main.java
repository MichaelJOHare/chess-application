package com.michaeljohare;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.michaeljohare.controller.ChessController;
import com.michaeljohare.model.Board;
import com.michaeljohare.view.ChessGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Board.initializeBoard();
        SwingUtilities.invokeLater(() -> {
            ChessController controller = new ChessController();
            ChessGUI gui = new ChessGUI();

            controller.setGUI(gui);
            gui.setController(controller);
        });
    }
}