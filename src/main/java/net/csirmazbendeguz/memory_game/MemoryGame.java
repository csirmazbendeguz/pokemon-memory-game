package net.csirmazbendeguz.memory_game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.game_state.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.swing.GameFrame;
import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

public class MemoryGame {

    /**
     * Start the game with 4x4 cards.
     */
    private static final int DEFAULT_BOARD_DIMENSION = 4;

    public static GameState gameState;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());
        ResourceLoader resourceLoader = injector.getInstance(ResourceLoader.class);
        RandomCardGenerator randomCardGenerator = injector.getInstance(RandomCardGenerator.class);

        GameFrame gameFrame = new GameFrame(resourceLoader);
        EventDispatcher eventDispatcher = new EventDispatcher(gameFrame);
        gameState = new GameState(eventDispatcher, randomCardGenerator);
        gameState.newGame(DEFAULT_BOARD_DIMENSION);
    }

}
