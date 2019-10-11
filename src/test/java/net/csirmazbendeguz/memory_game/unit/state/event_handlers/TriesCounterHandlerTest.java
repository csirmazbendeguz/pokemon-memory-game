package net.csirmazbendeguz.memory_game.unit.state.event_handlers;

import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;
import net.csirmazbendeguz.memory_game.state.TriesCounter;
import net.csirmazbendeguz.memory_game.state.event_handlers.TriesCounterHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TriesCounterHandlerTest {

    @Mock
    private TriesCounter mockTriesCounter;

    private TriesCounterHandler handler;

    @BeforeEach
    void setup() {
        handler = new TriesCounterHandler(mockTriesCounter);
    }

    @Test
    void testResetOnNewGame() {
        GameStartEvent mockEvent = mock(GameStartEvent.class);
        handler.gameStarted(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockTriesCounter).reset();
        verifyNoMoreInteractions(mockTriesCounter);
    }

    @Test
    void testIncrementOnFlipUp() {
        PairFlipUpEvent mockEvent = mock(PairFlipUpEvent.class);
        handler.pairFlippedUp(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockTriesCounter).increment();
        verifyNoMoreInteractions(mockTriesCounter);
    }

}
