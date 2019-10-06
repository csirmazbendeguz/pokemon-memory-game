package net.csirmazbendeguz.memory_game.event;

import com.google.inject.Singleton;

import java.util.*;

@Singleton
public class EventListeners {

    /**
     * The game's event listener interfaces mapped to their implementations.
     */
    private Map<Class, List<EventListener>> listeners = new HashMap<>();

    public void register(Class listenerInterface, EventListener listenerImplementation) {
        getAll(listenerInterface).add(listenerImplementation);
    }

    public List<EventListener> getAll(Class listenerInterface) {
        return listeners.computeIfAbsent(listenerInterface, i -> new ArrayList<>());
    }

}
