package net.csirmazbendeguz.memory_game.unit.state;

import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;
import net.csirmazbendeguz.memory_game.state.Card;
import net.csirmazbendeguz.memory_game.state.event_handlers.PairHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PairHandlerTest {

    @Mock
    private Card mockFirstCard, mockSecondCard;

    private PairHandler pairHandler;

    private PairFlipUpEvent event;

    @BeforeEach
    void setup() {
        pairHandler = new PairHandler();
        event = new PairFlipUpEvent(this, mockFirstCard, mockSecondCard);
    }

    @Test
    void testPairFound() {
        when(mockFirstCard.isPairOf(mockSecondCard)).thenReturn(true);
        pairHandler.pairFlippedUp(event);
        verify(mockFirstCard).hide();
        verify(mockSecondCard).hide();
    }

    @Test
    void testPairNotFound() {
        when(mockFirstCard.isPairOf(mockSecondCard)).thenReturn(false);
        pairHandler.pairFlippedUp(event);
        verify(mockFirstCard).flipDown();
        verify(mockSecondCard).flipDown();
    }

}
