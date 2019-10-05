package net.csirmazbendeguz.memory_game.event;

import com.google.inject.Singleton;

import java.util.*;

@Singleton
public class EventListeners {

    /**
     * The listener interfaces mapped to their implementations.
     */
    private Map<Class, List<EventListener>> listeners = new HashMap<>();

    public void add(Class listenerInterface, EventListener listener) {
        getAll(listenerInterface).add(listener);
    }

    public List<EventListener> getAll(Class listenerInterface) {
        return listeners.computeIfAbsent(listenerInterface, i -> new ArrayList<>());
    }

}
