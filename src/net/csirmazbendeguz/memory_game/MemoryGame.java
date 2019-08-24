package net.csirmazbendeguz.memory_game;

import net.csirmazbendeguz.memory_game.swing.GameFrame;

public class MemoryGame {

    /**
     * Start the game with 4x4 cards.
     */
    public static final int DEFAULT_BOARD_DIMENSION = 4;

    public static void main(String[] args) {
        new GameFrame();
    }

}
