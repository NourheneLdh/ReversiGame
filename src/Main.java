import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(); // Create the board
        board.initializeBoard();  // Initialize the board

        // Launch the GUI with the board
        SwingUtilities.invokeLater(() -> {
            new ReversiGUI(board); // Pass the board object to GUI
        });
    }
}