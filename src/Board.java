import java.util.ArrayList;
import java.util.List;

public class Board {
    private char[][] board = new char[8][8]; // 8x8 game board

    // Initialize the board with starting pieces
    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.'; // Empty spaces
            }
        }
        board[3][3] = 'W'; // White
        board[3][4] = 'B'; // Black
        board[4][3] = 'B'; // Black
        board[4][4] = 'W'; // White
    }

    // Returns the piece at a given position (for GUI)
    public char getPiece(int row, int col) {
        return board[row][col];
    }

    // Allows GUI to make a move
    public boolean makeMove(int row, int col, char player) {
        if (!isValidMove(row, col, player)) {
            return false;
        }
        board[row][col] = player;
        flipPieces(row, col, player);
        return true;
    }

    // Check if the move is valid
    public boolean isValidMove(int row, int col, char player) {
        if (board[row][col] != '.') {
            return false;
        }

        char opponent = (player == 'B') ? 'W' : 'B';
        int[][] directions = {
                {-1, 0}, {1, 0},  // Vertical
                {0, -1}, {0, 1},  // Horizontal
                {-1, -1}, {-1, 1}, // Diagonal (Up-Left, Up-Right)
                {1, -1}, {1, 1}   // Diagonal (Down-Left, Down-Right)
        };

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            boolean foundOpponent = false;

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                if (board[r][c] == opponent) {
                    foundOpponent = true;
                } else if (board[r][c] == player && foundOpponent) {
                    return true; // Valid move
                } else {
                    break;
                }
                r += direction[0];
                c += direction[1];
            }
        }

        return false;
    }

    // Flips opponent pieces after a valid move
    public void flipPieces(int row, int col, char player) {
        char opponent = (player == 'B') ? 'W' : 'B';
        int[][] directions = {
                {-1, 0}, {1, 0},  // Vertical
                {0, -1}, {0, 1},  // Horizontal
                {-1, -1}, {-1, 1}, // Diagonal
                {1, -1}, {1, 1}
        };

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            boolean foundOpponent = false;
            List<int[]> toFlip = new ArrayList<>();

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                if (board[r][c] == opponent) {
                    toFlip.add(new int[]{r, c});
                    foundOpponent = true;
                } else if (board[r][c] == player && foundOpponent) {
                    for (int[] cell : toFlip) {
                        board[cell[0]][cell[1]] = player;
                    }
                    break;
                } else {
                    break;
                }
                r += direction[0];
                c += direction[1];
            }
        }
    }

    // NEW: Checks if the player has any valid moves left
    public boolean hasValidMoves(char player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Calculates and returns the current score
    public int[] getScore() {
        int blackCount = 0, whiteCount = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 'B') blackCount++;
                else if (cell == 'W') whiteCount++;
            }
        }
        return new int[]{blackCount, whiteCount};
    }
}
