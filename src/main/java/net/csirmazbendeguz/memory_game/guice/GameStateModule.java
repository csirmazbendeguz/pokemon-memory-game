package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

public class GameStateModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceLoader.class).asEagerSingleton();
        bind(RandomCardGenerator.class).asEagerSingleton();
        bind(EventDispatcher.class).asEagerSingleton();
        bind(GameState.class).asEagerSingleton();
        bind(Stopwatch.class).asEagerSingleton();
        bind(TriesCounter.class).asEagerSingleton();
        bind(Board.class).asEagerSingleton();
    }

}
