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


    private void updateBoard() {
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
                currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';
                statusLabel.setText((currentPlayer == 'B') ? "● Player B's turn" : "○ Player W's turn");
                checkGameOver();
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
            String winner = (scores[0] > scores[1]) ? "Player B Wins!" : (scores[0] < scores[1]) ? "Player W Wins!" : "It's a Tie!";
            JOptionPane.showMessageDialog(this, "Game Over!\n" + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        new ReversiGUI(board);
    }
}
