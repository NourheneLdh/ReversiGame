package org.example;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board board = new Board(); // Create the board
            board.initializeBoard();  // Initialize the board

            ReversiGUI gameGUI = new ReversiGUI(board); // Store GUI instance

            // Ensure the application exits when the window is closed
            gameGUI.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        });
    }
}