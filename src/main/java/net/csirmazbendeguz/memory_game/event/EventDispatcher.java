package net.csirmazbendeguz.memory_game.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;

public class EventDispatcher {

    private Map<Class, List<EventListener>> listeners = new HashMap<>();

    public void addListener(Class eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>());
        listeners.get(eventType).add(listener);
    }

    /**
     * Dispatch an event to the registered event listeners.
     *
     * @param event The event to dispatch.
     */
    public void dispatch(EventObject event) {
        if (!listeners.containsKey(event.getClass())) {
            return;
        }
        for (EventListener listener : listeners.get(event.getClass())) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                // Interface methods are always public.
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }

                Class[] parameterTypes = method.getParameterTypes();
                // Only invoke methods that accept this event.
                if (parameterTypes.length != 1 || !parameterTypes[0].equals(event.getClass())) {
                    continue;
                }

                try {
                    method.invoke(listener, event);
                } catch (IllegalAccessException | InvocationTargetException exception) {
                    throw new RuntimeException(String.format("Failed to invoke method '%s'.", method), exception);
                }
            }
        }
    }

}
