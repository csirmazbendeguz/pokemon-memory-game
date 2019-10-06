package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import net.csirmazbendeguz.memory_game.event.EventListeners;
import net.csirmazbendeguz.memory_game.game_state.GameEndHandler;
import net.csirmazbendeguz.memory_game.game_state.PairHandler;
import net.csirmazbendeguz.memory_game.swing.GameFrame;
import org.reflections.Reflections;

import java.util.EventListener;
import java.util.Set;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PairHandler.class).asEagerSingleton();
        bind(GameEndHandler.class).asEagerSingleton();
        install(new ImageModule());
        bindListener(Matchers.any(), new ListenerRegistrator(getProvider(EventListeners.class), scanListenerInterfaces()));
        bind(GameFrame.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    EventListeners provideEventListeners() {
        return new EventListeners();
    }

    private Set<Class<? extends EventListener>> scanListenerInterfaces() {
        return new Reflections("net.csirmazbendeguz.memory_game.event.listeners").getSubTypesOf(EventListener.class);
    }

}
