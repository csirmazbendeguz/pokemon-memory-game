package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests for the tries counter.
 *
 * @see net.csirmazbendeguz.memory_game.game_state.TriesCounter
 */
@ExtendWith(MockitoExtension.class)
class TriesCounterTest {

    /**
     * The object to test.
     */
    private TriesCounter counter;

    /**
     * The counter's mock observer.
     */
    @Mock
    private Observer observer;

    /**
     * Set up the test fixture.
     */
    @BeforeEach
    void setup() {
        counter = new TriesCounter();
        counter.addObserver(observer);
    }

    /**
     * Test that the counter is set to 0 by default.
     */
    @Test
    void testInitialValue() {
        assertEquals(0, counter.getTries());
        verifyZeroInteractions(observer);
    }

    /**
     * Test that the counter can be incremented by 1.
     */
    @Test
    void testIncrement() {
        counter.increment();
        assertEquals(1, counter.getTries());
        verify(observer).update(counter, 1);
    }

    /**
     * Test that the counter can be reset to 0.
     */
    @Test
    void testReset() {
        counter.reset();
        assertEquals(0, counter.getTries());
        verify(observer).update(counter, 0);
    }

}
