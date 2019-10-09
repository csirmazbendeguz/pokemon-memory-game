package net.csirmazbendeguz.memory_game.event;

import java.util.*;

public class EventHandlers {

    /**
     * The game's event listener interfaces mapped to their implementations.
     */
    private Map<Class, List<EventListener>> handlers = new HashMap<>();

    public void register(Class listenerInterface, EventListener handler) {
        getAll(listenerInterface).add(handler);
    }

    public List<EventListener> getAll(Class listenerInterface) {
        return handlers.computeIfAbsent(listenerInterface, i -> new ArrayList<>());
    }

    public void remove(Class listInterface, EventListener handler) {
        getAll(listInterface).remove(handler);
    }

}
