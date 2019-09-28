package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.TimerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    Timer mockTimer() {
        Timer mockTimer = mock(Timer.class);
        when(mockTimerFactory.create()).thenReturn(mockTimer);
        return mockTimer;
    }

    <T extends CardEvent> void assertEventDispatched(Class<T> c) {
        ArgumentCaptor<T> eventCaptor = ArgumentCaptor.forClass(c);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        CardEvent event = eventCaptor.getValue();
        assertEquals(card, event.getSource());
        assertEquals(card, event.getCard());
    }

    @Test
    void testDefaults() {
        assertFalse(card.isFaceUp());
        assertTrue(card.isVisible());
        assertFalse(card.isInAnimation());
        assertEquals("bulbasaur.png", card.getImageName());
        assertTrue(card.canFlipUp());
    }

    @Test
    void testFlipUp() {
        card.flipUp();
        assertTrue(card.isFaceUp());
        assertTrue(card.isVisible());
        assertFalse(card.isInAnimation());
        assertFalse(card.canFlipUp());
        assertEventDispatched(CardFlipUpEvent.class);
    }

    @Test
    void testIsPairOf() {
        Card pair = new Card("bulbasaur.png", mockTimerFactory, mockEventDispatcher);
        assertTrue(card.isPairOf(pair));
        assertTrue(pair.isPairOf(card));

        Card notPair = new Card("charmander.png", mockTimerFactory, mockEventDispatcher);
        assertFalse(card.isPairOf(notPair));
        assertFalse(notPair.isPairOf(card));
    }

    @Test
    void testHide() {
        Timer mockTimer = mockTimer();

        card.hide();

        assertTrue(card.isVisible());
        assertTrue(card.isInAnimation());
        assertFalse(card.canFlipUp());
        verify(mockTimer).schedule(any(TimerTask.class), eq(Card.ANIMATION_LENGTH));
    }

    @Test
    void testHideTimerTask() {
        Timer mockTimer = mockTimer();
        card.hide();

        ArgumentCaptor<TimerTask> timerTaskCaptor = ArgumentCaptor.forClass(TimerTask.class);
        verify(mockTimer).schedule(timerTaskCaptor.capture(), eq(Card.ANIMATION_LENGTH));
        TimerTask timerTask = timerTaskCaptor.getValue();
        timerTask.run();

        assertFalse(card.isVisible());
        assertFalse(card.isInAnimation());
        assertFalse(card.canFlipUp());
        assertEventDispatched(CardHideEvent.class);
    }

}
