package net.csirmazbendeguz.memory_game.unit.game_state;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TriesCounterTest {

    private TriesCounter counter;

    @Mock
    private Observer mockObserver;

    @BeforeEach
    void setup() {
        counter = new TriesCounter();
        counter.addObserver(mockObserver);
    }

    /**
     * Test that the counter is set to 0 by default.
     */
    @Test
    void testInitialValue() {
        assertEquals(0, counter.getTries());
        verifyZeroInteractions(mockObserver);
    }

    /**
     * Test that the counter can be incremented by 1.
     */
    @Test
    void testIncrement() {
        counter.increment();
        assertEquals(1, counter.getTries());
        verify(mockObserver).update(counter, 1);
    }

    /**
     * Test that the counter can be reset to 0.
     */
    @Test
    void testReset() {
        counter.reset();
        assertEquals(0, counter.getTries());
        verify(mockObserver).update(counter, 0);
    }

}
