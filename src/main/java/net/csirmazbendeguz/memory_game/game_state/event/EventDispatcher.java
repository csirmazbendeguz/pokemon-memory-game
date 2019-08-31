package net.csirmazbendeguz.memory_game.game_state.event;

import net.csirmazbendeguz.memory_game.util.ComponentTreeIterator;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.EventListener;
import java.util.EventObject;

public class EventDispatcher {

    private Component root;

    public EventDispatcher(Component root) {
        this.root = root;
    }

    /**
     * Dispatch an event to components implementing the corresponding event listener interface.
     *
     * @param event The event to dispatch.
     */
    public void dispatch(EventObject event) {
        new ComponentTreeIterator(root).forEachRemaining(component -> {
            // Events can only be dispatched to event listeners.
            if (!(component instanceof EventListener)) {
                return;
            }

            for (Method method : component.getClass().getDeclaredMethods()) {
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
                    method.invoke(component, event);
                } catch (IllegalAccessException | InvocationTargetException exception) {
                    throw new RuntimeException(String.format("Failed to invoke method '%s'.", method), exception);
                }
            }
        });
    }

}
