package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import net.csirmazbendeguz.memory_game.event.EventHandlers;

import java.util.EventListener;
import java.util.Set;

public class EventHandlerRegistrator implements TypeListener {

    private Provider<EventHandlers> eventHandlersProvider;

    /**
     * The event handler types to register.
     */
    private Set<Class<? extends EventListener>> listenerInterfaces;

    public EventHandlerRegistrator(Provider<EventHandlers> eventHandlersProvider, Set<Class<? extends EventListener>> listenerInterfaces) {
        this.eventHandlersProvider = eventHandlersProvider;
        this.listenerInterfaces = listenerInterfaces;
    }

    /**
     * Automatically register the event handlers found by Guice.
     */
    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        for (Class<? extends EventListener> listenerInterface : listenerInterfaces) {
            if (listenerInterface.isAssignableFrom(type.getRawType())) {
                encounter.register((InjectionListener<I>) injectee -> {
                    eventHandlersProvider.get().register(listenerInterface, (EventListener) injectee);
                });
            }
        }
    }

}
