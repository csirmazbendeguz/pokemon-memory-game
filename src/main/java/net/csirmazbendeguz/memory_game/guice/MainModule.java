package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.game_state.PairHandler;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PairHandler.class).asEagerSingleton();
        install(new ImageModule());
    }

}
