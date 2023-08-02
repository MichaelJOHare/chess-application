package com.michaeljohare.controller;

import java.io.*;

public class StockfishController {
    private Process stockfishProcess;
    private BufferedReader stockfishReader;

    public StockfishController() {
        try {
            String stockfishPath = StockfishController.class.getResource("/stockfish").getPath();
            ProcessBuilder builder = new ProcessBuilder(stockfishPath);
            builder.redirectErrorStream(true);
            stockfishProcess = builder.start();
            stockfishReader = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));

            sendCommand("uci");
            sendCommand("setoption name Skill Level value 10");
            waitForReady();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        try {
            stockfishProcess.getOutputStream().write((command + "\n").getBytes());
            stockfishProcess.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String waitForReady() throws IOException {
        String response;
        while ((response = stockfishReader.readLine()) != null) {
            if (response.equals("readyok")) {
                break;
            }
        }
        return response;
    }

    public String getBestMove(String fenPosition) {
        String response = "";
        sendCommand("position fen " + fenPosition);
        sendCommand("go movetime 1000");
        try {
            response = waitForReady();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parts = response.split(" ");
        return parts[1];
    }

    public void stopStockfish() {
        sendCommand("quit");
        stockfishProcess.destroy();
    }
}
