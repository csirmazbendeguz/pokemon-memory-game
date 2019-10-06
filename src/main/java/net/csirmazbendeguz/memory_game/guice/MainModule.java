package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.game_state.event_handlers.GameEndHandler;
import net.csirmazbendeguz.memory_game.game_state.event_handlers.PairHandler;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind the event handlers.
        // Most objects are bound lazily when requested as dependencies.
        // Event handlers are consumers, not dependencies, so they have to be bound eagerly.
        bind(PairHandler.class).asEagerSingleton();
        bind(GameEndHandler.class).asEagerSingleton();

        // Start the GUI by binding the game frame eagerly.
        bind(GameFrame.class).asEagerSingleton();

        install(new ImageProvidersModule());
        install(new ListenerDiscoveryModule());
    }

}
