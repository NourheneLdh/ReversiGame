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
    void testMakeMoveAndFlipPieces() {
        Board board = new Board();
        board.initializeBoard();

        assertTrue(board.makeMove(2, 3, 'B'), "Move should be successful");
        assertEquals('B', board.getPiece(2, 3), "Expected B at (2,3) after move");
        assertEquals('B', board.getPiece(3, 3), "Expected flipped piece at (3,3)");
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
