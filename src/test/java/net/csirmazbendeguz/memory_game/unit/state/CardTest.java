package net.csirmazbendeguz.memory_game.unit.state;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipDownEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.state.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardTest extends MockTimerFactoryTestBase {

    private Card card;

    @Mock
    private EventDispatcher mockEventDispatcher;

    @BeforeEach
    void setup() {
        card = new Card("bulbasaur.png", mockTimerFactory, mockEventDispatcher);
    }

    void assertCardEvent(CardEvent event) {
        assertEquals(card, event.getSource());
        assertEquals(card, event.getCard());
    }

    @Test
    void testDefaults() {
        assertFalse(card.isFaceUp());
        assertTrue(card.isVisible());
        assertEquals("bulbasaur.png", card.getImageName());
        assertTrue(card.canFlipUp());
    }

    @Test
    void testFlipUp() {
        card.flipUp();

        assertTrue(card.isFaceUp());
        assertTrue(card.isVisible());
        assertFalse(card.canFlipUp());

        ArgumentCaptor<CardFlipUpEvent> eventCaptor = ArgumentCaptor.forClass(CardFlipUpEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        assertCardEvent(eventCaptor.getValue());
    }

    @Test
    void testIsPairOf() {
        Card other = new Card("bulbasaur.png", mockTimerFactory, mockEventDispatcher);
        assertTrue(card.isPairOf(other));
        assertTrue(other.isPairOf(card));
    }

    @Test
    void testNotIsPairOf() {
        Card other = new Card("charmander.png", mockTimerFactory, mockEventDispatcher);
        assertFalse(card.isPairOf(other));
        assertFalse(other.isPairOf(card));
    }

    @Test
    void testHide() {
        Timer mockTimer = mockTimer();

        card.hide();

        assertTrue(card.isVisible());
        assertFalse(card.canFlipUp());

        ArgumentCaptor<TimerTask> timerTaskCaptor = ArgumentCaptor.forClass(TimerTask.class);
        verify(mockTimer).schedule(timerTaskCaptor.capture(), eq(Card.ANIMATION_DELAY));
        TimerTask scheduledTask = timerTaskCaptor.getValue();
        scheduledTask.run();

        assertFalse(card.isVisible());
        assertFalse(card.canFlipUp());

        ArgumentCaptor<CardHideEvent> eventCaptor = ArgumentCaptor.forClass(CardHideEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        assertCardEvent(eventCaptor.getValue());
    }

    @Test
    void testFlipDown() {
        Timer mockTimer = mockTimer();

        card.flipUp();
        card.flipDown();

        assertTrue(card.isFaceUp());
        assertFalse(card.canFlipUp());

        ArgumentCaptor<TimerTask> timerTaskCaptor = ArgumentCaptor.forClass(TimerTask.class);
        verify(mockTimer).schedule(timerTaskCaptor.capture(), eq(Card.ANIMATION_DELAY));
        TimerTask scheduledTask = timerTaskCaptor.getValue();
        scheduledTask.run();

        assertFalse(card.isFaceUp());
        assertTrue(card.canFlipUp());

        InOrder inOrder = inOrder(mockEventDispatcher);
        inOrder.verify(mockEventDispatcher).dispatch(any(CardFlipUpEvent.class));
        ArgumentCaptor<CardFlipDownEvent> eventCaptor = ArgumentCaptor.forClass(CardFlipDownEvent.class);
        inOrder.verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        assertCardEvent(eventCaptor.getValue());
    }

}
