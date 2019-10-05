package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import net.csirmazbendeguz.memory_game.event.EventListeners;
import org.reflections.Reflections;

import java.util.EventListener;
import java.util.Set;

public class ListenerRegistrator implements TypeListener {

    private Provider<EventListeners> eventListenersProvider;

    private Set<Class<? extends EventListener>> listenerInterfaces;

    public ListenerRegistrator(Provider<EventListeners> eventListenersProvider) {
        this.eventListenersProvider = eventListenersProvider;
        listenerInterfaces = new Reflections("net.csirmazbendeguz.memory_game.event.listeners")
            .getSubTypesOf(EventListener.class);
    }

    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        for (Class listenerInterface : listenerInterfaces) {
            if (listenerInterface.isAssignableFrom(type.getRawType())) {
                encounter.register((InjectionListener<I>) injectee -> {
                    EventListeners eventListeners = eventListenersProvider.get();
                    eventListeners.add(listenerInterface, (EventListener) injectee);
                });
            }
        }
    }

}
