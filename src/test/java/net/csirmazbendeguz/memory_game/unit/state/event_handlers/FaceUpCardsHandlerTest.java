package net.csirmazbendeguz.memory_game.unit.state.event_handlers;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;
import net.csirmazbendeguz.memory_game.state.Card;
import net.csirmazbendeguz.memory_game.state.event_handlers.FaceUpCardsHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FaceUpCardsHandlerTest {

    @Mock
    private EventDispatcher mockEventDispatcher;

    @Mock
    private Card mockFirstCard, mockSecondCard;

    private FaceUpCardsHandler faceUpCardsHandler;

    @BeforeEach
    void setup() {
        faceUpCardsHandler = new FaceUpCardsHandler(mockEventDispatcher);
    }

    @Test
    void testDispatch() {
        faceUpCardsHandler.cardFlippedUp(new CardFlipUpEvent(this, mockFirstCard));
        verifyZeroInteractions(mockEventDispatcher);

        faceUpCardsHandler.cardFlippedUp(new CardFlipUpEvent(this, mockSecondCard));
        ArgumentCaptor<PairFlipUpEvent> eventCaptor = ArgumentCaptor.forClass(PairFlipUpEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        PairFlipUpEvent event = eventCaptor.getValue();
        assertSame(mockFirstCard, event.getFirstCard());
        assertSame(mockSecondCard, event.getSecondCard());
    }

}
