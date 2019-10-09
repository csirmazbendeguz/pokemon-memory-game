package net.csirmazbendeguz.memory_game.unit.event;

import net.csirmazbendeguz.memory_game.event.EventHandlers;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EventListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventHandlersTest {

    private EventHandlers eventHandlers;

    @BeforeEach
    void setup() {
        eventHandlers = new EventHandlers();
    }

    @Test
    void testEmpty() {
        assertTrue(eventHandlers.getAll(GameStartListener.class).isEmpty());
    }

    @Test
    void testRegister() {
        EventListener mockHandler = Mockito.mock(EventListener.class);
        eventHandlers.register(GameStartListener.class, mockHandler);
        List<EventListener> gameStartListeners = eventHandlers.getAll(GameStartListener.class);
        assertEquals(1, gameStartListeners.size());
        assertSame(mockHandler, gameStartListeners.get(0));
    }

}
