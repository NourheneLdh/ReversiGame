package org.example;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.core.GenericTypeMatcher;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.assertj.swing.edt.GuiActionRunner.execute;

public class ReversiGUITest {
    private FrameFixture window;
    private ReversiGUI gui;
    private Board board;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeBoard();

        gui = execute(() -> new ReversiGUI(board));
        window = new FrameFixture(gui);
        window.show();
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testWindowOpens() {
        window.requireVisible();
    }

    @Test
    public void testValidMoveUpdatesBoard() throws Exception {
        // Click button for a valid move (2,3)
        window.button("button_2_3").click();

        // 🔹 Allow time for GUI to process move and repaint
        Thread.sleep(1000);
        SwingUtilities.invokeAndWait(() -> {});

        // 🔹 Debugging: Print board state & button text
        System.out.println("DEBUG: Board piece at (2,3) after move: " + board.getPiece(2, 3));
        System.out.println("DEBUG: GUI button text at (2,3): " + window.button("button_2_3").text());

        // 🔹 Check if the board updated
        Assertions.assertNotEquals('.', board.getPiece(2, 3), "Board should update after a valid move");
    }

    @Test
    public void testInvalidMoveShowsMessage() {
        // Click a button where a move is invalid (e.g., a random empty cell)
        window.button("button_0_0").click();

        // Small delay to allow message to appear
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        // Check if an error message appears
        JOptionPaneFixture optionPane = window.optionPane();
        optionPane.requireMessage("Invalid move! Try again.");
    }
}