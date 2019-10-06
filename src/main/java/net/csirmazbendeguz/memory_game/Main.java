package net.csirmazbendeguz.memory_game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.csirmazbendeguz.memory_game.state.GameState;
import net.csirmazbendeguz.memory_game.guice.MainModule;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Injector injector = Guice.createInjector(new MainModule());
            injector.getInstance(GameState.class).newGame();
        });
    }

}
