package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.TimerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CardTest {

    private Card card;

    @Mock
    private TimerFactory mockTimerFactory;

    @Mock
    private EventDispatcher mockEventDispatcher;

    @BeforeEach
    void setup() {
        card = new Card("bulbasaur.png", mockTimerFactory, mockEventDispatcher);
    }

    @Test
    void testDefaults() {
        assertFalse(card.isFaceUp());
        assertTrue(card.isVisible());
        assertFalse(card.isInAnimation());
        assertEquals("bulbasaur.png", card.getImageName());
    }

    @Test
    void testFlipUp() {
        card.flipUp();
        assertTrue(card.isFaceUp());
        ArgumentCaptor<CardFlipUpEvent> eventCaptor = ArgumentCaptor.forClass(CardFlipUpEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        CardFlipUpEvent event = eventCaptor.getValue();
        assertEquals(card, event.getSource());
        assertEquals(card, event.getCard());
    }

}
