package net.csirmazbendeguz.memory_game;

import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.game_state.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

public class MemoryGame {

    /**
     * Start the game with 4x4 cards.
     */
    private static final int DEFAULT_BOARD_DIMENSION = 4;

    public static GameState gameState;

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        EventDispatcher eventDispatcher = new EventDispatcher(gameFrame);
        gameState = new GameState(eventDispatcher);
        gameState.newGame(DEFAULT_BOARD_DIMENSION);
    }

}
