package net.csirmazbendeguz.memory_game.unit.state.event_handlers;

import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.state.Stopwatch;
import net.csirmazbendeguz.memory_game.state.event_handlers.StopwatchHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopwatchHandlerTest {

    @Mock
    private Stopwatch mockStopwatch;

    private StopwatchHandler handler;

    @BeforeEach
    void setup() {
        handler = new StopwatchHandler(mockStopwatch);
    }

    @Test
    void testStopOnGameEnd() {
        GameEndEvent mockEvent = mock(GameEndEvent.class);
        handler.gameEnded(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockStopwatch).stop();
        verifyNoMoreInteractions(mockStopwatch);
    }

    @Test
    void testResetOnGameStart() {
        when(mockStopwatch.isRunning()).thenReturn(false);
        GameStartEvent mockEvent = mock(GameStartEvent.class);
        handler.gameStarted(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockStopwatch).reset();
        verifyNoMoreInteractions(mockStopwatch);
    }

    @Test
    void testStopAndResetOnGameStart() {
        when(mockStopwatch.isRunning()).thenReturn(true);
        GameStartEvent mockEvent = mock(GameStartEvent.class);
        handler.gameStarted(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockStopwatch).stop();
        verify(mockStopwatch).reset();
        verifyNoMoreInteractions(mockStopwatch);
    }

    @Test
    void testStartOnFirstFlip() {
        when(mockStopwatch.isRunning()).thenReturn(false);
        CardFlipUpEvent mockEvent = mock(CardFlipUpEvent.class);
        handler.cardFlippedUp(mockEvent);
        verifyZeroInteractions(mockEvent);
        verify(mockStopwatch).start();
        verifyNoMoreInteractions(mockStopwatch);
    }

    @Test
    void testContinueOnFlip() {
        when(mockStopwatch.isRunning()).thenReturn(true);
        CardFlipUpEvent mockEvent = mock(CardFlipUpEvent.class);
        handler.cardFlippedUp(mockEvent);
        verifyZeroInteractions(mockEvent);
        verifyNoMoreInteractions(mockStopwatch);
    }

}
