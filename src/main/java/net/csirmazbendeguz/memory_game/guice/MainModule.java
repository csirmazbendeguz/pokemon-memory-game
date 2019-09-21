package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new GameStateModule());
        install(new ImageModule());
    }

}
