package net.csirmazbendeguz.memory_game;

import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

public class MemoryGame {

    /**
     * Start the game with 4x4 cards.
     */
    private static final int DEFAULT_BOARD_DIMENSION = 4;

    public static void main(String[] args) {
        new GameFrame();
        Board.getInstance().newGame(MemoryGame.DEFAULT_BOARD_DIMENSION);
    }

}
