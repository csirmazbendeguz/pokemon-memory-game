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

@ExtendWith(MockitoExtension.class)
class TriesCounterTest {

    private TriesCounter triesCounter;

    @Mock
    private Observer mockObserver;

    @BeforeEach
    void setup() {
        triesCounter = new TriesCounter();
        triesCounter.addObserver(mockObserver);
    }

    @Test
    void testInitialValue() {
        assertEquals(0, triesCounter.getTries());
        verifyZeroInteractions(mockObserver);
    }

    @Test
    void testIncrement() {
        triesCounter.increment();
        assertEquals(1, triesCounter.getTries());
        verify(mockObserver).update(triesCounter, 1);
    }

    @Test
    void testReset() {
        triesCounter.reset();
        assertEquals(0, triesCounter.getTries());
        verify(mockObserver).update(triesCounter, 0);
    }

}
