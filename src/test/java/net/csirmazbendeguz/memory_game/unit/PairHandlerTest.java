package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.PairHandler;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PairHandlerTest {

    @Mock
    private TriesCounter mockTriesCounter;

    @Mock
    private Card mockCard1, mockCard2;

    private PairHandler pairHandler;

    @BeforeEach
    void setup() {
        pairHandler = new PairHandler(mockTriesCounter);
    }

    @Test
    void testPairs() {
        when(mockCard1.isPairOf(mockCard2)).thenReturn(true);

        pairHandler.cardFlippedUp(new CardFlipUpEvent(this, mockCard1));
        verifyZeroInteractions(mockCard1);
        verifyZeroInteractions(mockCard2);
        verifyZeroInteractions(mockTriesCounter);

        pairHandler.cardFlippedUp(new CardFlipUpEvent(this, mockCard2));
        verify(mockCard1).hide();
        verify(mockCard2).hide();
        verify(mockTriesCounter).increment();
    }

    @Test
    void testNotPairs() {
        when(mockCard1.isPairOf(mockCard2)).thenReturn(false);

        pairHandler.cardFlippedUp(new CardFlipUpEvent(this, mockCard1));
        verifyZeroInteractions(mockCard1);
        verifyZeroInteractions(mockCard2);
        verifyZeroInteractions(mockTriesCounter);

        pairHandler.cardFlippedUp(new CardFlipUpEvent(this, mockCard2));
        verify(mockCard1).flipDown();
        verify(mockCard2).flipDown();
        verify(mockTriesCounter).increment();
    }

}
