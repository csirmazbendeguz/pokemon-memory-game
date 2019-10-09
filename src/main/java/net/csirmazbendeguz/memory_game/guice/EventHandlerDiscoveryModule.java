package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import net.csirmazbendeguz.memory_game.event.EventHandlers;
import org.reflections.Reflections;

import java.util.EventListener;
import java.util.Set;

public class EventHandlerDiscoveryModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new EventHandlerRegistrator(
            getProvider(EventHandlers.class),
            scanListenerInterfaces()
        ));
    }

    @Provides
    @Singleton
    EventHandlers provideEventHandlers() {
        return new EventHandlers();
    }

    private Set<Class<? extends EventListener>> scanListenerInterfaces() {
        return new Reflections("net.csirmazbendeguz.memory_game.event.listeners")
            .getSubTypesOf(EventListener.class);
    }

}
