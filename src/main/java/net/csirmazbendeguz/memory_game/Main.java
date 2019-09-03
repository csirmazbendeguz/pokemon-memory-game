package net.csirmazbendeguz.memory_game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

import javax.swing.*;

public class Main {

    /**
     * Start the game with 4x4 cards.
     */
    private static final int DEFAULT_BOARD_DIMENSION = 4;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Injector injector = Guice.createInjector(new BasicModule());
            injector.getInstance(GameFrame.class);
            injector.getInstance(GameState.class).newGame(DEFAULT_BOARD_DIMENSION);
        });
    }

}
