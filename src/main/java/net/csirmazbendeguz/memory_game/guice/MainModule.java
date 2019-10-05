package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import net.csirmazbendeguz.memory_game.event.EventListeners;
import net.csirmazbendeguz.memory_game.game_state.PairHandler;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PairHandler.class).asEagerSingleton();
        install(new ImageModule());
        bindListener(Matchers.any(), new ListenerRegistrator(getProvider(EventListeners.class)));
    }

    @Provides
    @Singleton
    EventListeners provideEventListeners() {
        return new EventListeners();
    }

}
