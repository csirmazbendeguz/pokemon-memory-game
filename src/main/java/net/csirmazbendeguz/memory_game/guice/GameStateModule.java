package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;

public class GameStateModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventDispatcher.class).asEagerSingleton();
        bind(GameState.class).asEagerSingleton();
        bind(Stopwatch.class).asEagerSingleton();
        bind(TriesCounter.class).asEagerSingleton();
        bind(Board.class).asEagerSingleton();
    }

}
