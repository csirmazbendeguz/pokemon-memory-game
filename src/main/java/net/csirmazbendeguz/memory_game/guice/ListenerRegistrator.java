package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import net.csirmazbendeguz.memory_game.event.EventListeners;

import java.util.EventListener;
import java.util.Set;

public class ListenerRegistrator implements TypeListener {

    private Provider<EventListeners> eventListenersProvider;

    private Set<Class<? extends EventListener>> listenerInterfaces;

    public ListenerRegistrator(Provider<EventListeners> eventListenersProvider, Set<Class<? extends EventListener>> listenerInterfaces) {
        this.eventListenersProvider = eventListenersProvider;
        this.listenerInterfaces = listenerInterfaces;
    }

    /**
     * Register the game's event listener implementations.
     */
    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        for (Class<? extends EventListener> listenerInterface : listenerInterfaces) {
            if (listenerInterface.isAssignableFrom(type.getRawType())) {
                encounter.register((InjectionListener<I>) injectee -> {
                    eventListenersProvider.get().register(listenerInterface, (EventListener) injectee);
                });
            }
        }
    }

}
