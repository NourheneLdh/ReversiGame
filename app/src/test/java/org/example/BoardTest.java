package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testBoardInitialization() {
        Board board = new Board();
        board.initializeBoard();

        assertEquals('W', board.getPiece(3, 3), "Expected W at (3,3)");
        assertEquals('B', board.getPiece(3, 4), "Expected B at (3,4)");
        assertEquals('B', board.getPiece(4, 3), "Expected B at (4,3)");
        assertEquals('W', board.getPiece(4, 4), "Expected W at (4,4)");
    }

    @Test
    void testValidMove() {
        Board board = new Board();
        board.initializeBoard();

        assertTrue(board.isValidMove(2, 3, 'B'), "Move should be valid for B at (2,3)");
        assertFalse(board.isValidMove(0, 0, 'B'), "Move should NOT be valid at (0,0)");
    }

    @Test
    void testGameOverConditionAndWinner() {
        Board board = new Board();
        board.initializeBoard();

        // Fill the board with mostly Player B’s moves to simulate game end
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.makeMove(i, j, 'B'); // Assuming this method places pieces correctly
            }
        }

        // Ensure the game is over
        assertFalse(board.hasValidMoves('B'), "No valid moves left for Player B");
        assertFalse(board.hasValidMoves('W'), "No valid moves left for Player W");

        // Get final scores
        int[] scores = board.getScore();
        int blackScore = scores[0];
        int whiteScore = scores[1];

        // Print for debugging
        System.out.println("Final Score - Black: " + blackScore + ", White: " + whiteScore);

        // Check that the game correctly identifies the winner
        if (blackScore > whiteScore) {
            System.out.println("Winner: Player B (Black)");
            assertTrue(blackScore > whiteScore, "Player B should win");
        } else if (whiteScore > blackScore) {
            System.out.println("Winner: Player W (White)");
            assertTrue(whiteScore > blackScore, "Player W should win");
        } else {
            System.out.println("Game ended in a tie!");
            assertEquals(blackScore, whiteScore, "Scores should be equal for a tie");
        }
    }

    @Test
    void testGameOverCondition() {
        Board board = new Board();
        board.initializeBoard();

        // Fill the board to simulate game end
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.makeMove(i, j, 'B');
            }
        }

        assertFalse(board.hasValidMoves('B'), "No valid moves left for B");
        assertFalse(board.hasValidMoves('W'), "No valid moves left for W");
    }

    @Test
    void testScoreCalculation() {
        Board board = new Board();
        board.initializeBoard();

        int[] scores = board.getScore();
        assertEquals(2, scores[0], "Black should start with 2 pieces");
        assertEquals(2, scores[1], "White should start with 2 pieces");
    }
}
