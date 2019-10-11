package net.csirmazbendeguz.memory_game.unit.state.event_handlers;


import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.state.Board;
import net.csirmazbendeguz.memory_game.state.Stopwatch;
import net.csirmazbendeguz.memory_game.state.TriesCounter;
import net.csirmazbendeguz.memory_game.state.event_handlers.GameEndHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEndHandlerTest {

    @Mock
    private Board mockBoard;

    @Mock
    private Stopwatch mockStopwatch;

    @Mock
    private TriesCounter mockTriesCounter;

    @Mock
    private EventDispatcher mockEventDispatcher;

    private GameEndHandler handler;

    @BeforeEach
    void setup() {
        handler = new GameEndHandler(mockBoard, mockStopwatch, mockTriesCounter, mockEventDispatcher);
    }

    @Test
    void testNoEventDispatched() {
        when(mockBoard.isEmpty()).thenReturn(false);
        triggerCardHidden();
        verifyZeroInteractions(mockEventDispatcher);
    }

    @Test
    void testEventDispatched() {
        when(mockBoard.isEmpty()).thenReturn(true);
        when(mockBoard.getDimension()).thenReturn(1);
        when(mockStopwatch.getSeconds()).thenReturn(2);
        when(mockTriesCounter.getTries()).thenReturn(3);
        triggerCardHidden();
        ArgumentCaptor<GameEndEvent> eventCaptor = ArgumentCaptor.forClass(GameEndEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        GameEndEvent event = eventCaptor.getValue();
        assertSame(1, event.getDimension());
        assertSame(2, event.getSeconds());
        assertSame(3, event.getTries());
    }

    void triggerCardHidden() {
        CardHideEvent mockEvent = mock(CardHideEvent.class);
        handler.cardHidden(mockEvent);
        verifyZeroInteractions(mockEvent);
    }

}
