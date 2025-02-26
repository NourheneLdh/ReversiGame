package org.example;
import javax.swing.*;
import java.awt.*;

public class ReversiGUI extends JFrame {
    private Board board;
    private JButton[][] cells;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private char currentPlayer = 'B';

    public ReversiGUI(Board board) {
        this.board = board;
        this.cells = new JButton[8][8];
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Reversi Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton cell = new JButton();
                cell.setOpaque(true);
                cell.setBackground(Color.LIGHT_GRAY);
                cell.setFont(new Font("Arial", Font.BOLD, 24));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // **✅ Give each button a unique name**
                cell.setName("button_" + i + "_" + j);

                int row = i;
                int col = j;
                cell.addActionListener(e -> handleMove(row, col));

                cells[i][j] = cell;
                boardPanel.add(cell);
            }
        }

        statusLabel = new JLabel("● Player B's turn", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: ● 2 - 2 ○", SwingConstants.CENTER);

        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);

        setSize(600, 600);
        setVisible(true);

        updateBoard();
    }


    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char piece = board.getPiece(i, j);
                if (piece == 'B') {
                    cells[i][j].setText("●");
                    cells[i][j].setForeground(Color.BLACK);
                } else if (piece == 'W') {
                    cells[i][j].setText("○");
                    cells[i][j].setForeground(Color.WHITE);
                } else {
                    cells[i][j].setText(""); // Empty cell
                }
            }
        }
        updateScore();
    }

    private void handleMove(int row, int col) {
        SwingUtilities.invokeLater(() -> {
            if (board.makeMove(row, col, currentPlayer)) {
                updateBoard();  // Ensure board updates in the UI
                this.repaint();  // Force repaint

                // Switch turn
                currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';

                // 🔍 Check if the new player has valid moves
                if (!board.hasValidMoves(currentPlayer)) {
                    System.out.println("No valid moves for " + currentPlayer + ". Skipping turn...");
                    JOptionPane.showMessageDialog(this, "No valid moves for " + currentPlayer + ". Skipping turn...");

                    // Skip turn to the other player
                    currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';

                    // If both players have no moves, call checkGameOver()
                    if (!board.hasValidMoves(currentPlayer)) {
                        checkGameOver();  // ✅ Call this instead of returning
                        return;  // ✅ Exit only after checking game over
                    }
                }

                statusLabel.setText((currentPlayer == 'B') ? "● Player B's turn" : "○ Player W's turn");
                checkGameOver();  // ✅ Ensure checkGameOver() is always called
            } else {
                JOptionPane.showMessageDialog(this, "Invalid move! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private void updateScore() {
        int[] scores = board.getScore();
        scoreLabel.setText("Score: ● " + scores[0] + " - " + scores[1] + " ○");
    }

    private void checkGameOver() {
        if (!board.hasValidMoves('B') && !board.hasValidMoves('W')) {
            int[] scores = board.getScore();
            String winnerMessage;

            if (scores[0] > scores[1]) {
                winnerMessage = "Game Over! 🎉 ● Player B wins with " + scores[0] + " points!";
            } else if (scores[1] > scores[0]) {
                winnerMessage = "Game Over! 🎉 ○ Player W wins with " + scores[1] + " points!";
            } else {
                winnerMessage = "Game Over! It's a tie! 🤝";
            }

            JOptionPane.showMessageDialog(this, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);

            // 🔹 Disable all buttons so players cannot click after the game ends
            disableBoard();
        }
    }

    // ✅ Helper method to disable board buttons after game ends
    private void disableBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        new ReversiGUI(board);
    }
}