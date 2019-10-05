package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.event.EventListeners;
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
class EventListenersTest {

    private EventListeners eventListeners;

    @BeforeEach
    void setup() {
        eventListeners = new EventListeners();
    }

    @Test
    void testEmpty() {
        assertTrue(eventListeners.getAll(GameStartListener.class).isEmpty());
    }

    @Test
    void testAdd() {
        EventListener mockListener = Mockito.mock(EventListener.class);
        eventListeners.add(GameStartListener.class, mockListener);
        List<EventListener> gameStartListeners = eventListeners.getAll(GameStartListener.class);
        assertEquals(1, gameStartListeners.size());
        assertSame(mockListener, gameStartListeners.get(0));
    }

}
