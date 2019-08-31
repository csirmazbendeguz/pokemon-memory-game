package net.csirmazbendeguz.memory_game;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceLoader.class).asEagerSingleton();
        bind(RandomCardGenerator.class).asEagerSingleton();
    }

}
