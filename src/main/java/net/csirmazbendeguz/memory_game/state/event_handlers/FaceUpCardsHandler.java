package net.csirmazbendeguz.memory_game.state.event_handlers;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;
import net.csirmazbendeguz.memory_game.state.Card;

import java.util.ArrayDeque;
import java.util.Queue;

public class FaceUpCardsHandler implements GameStartListener, CardFlipUpListener {

    private EventDispatcher eventDispatcher;

    /**
     * The cards facing up in the order they were flipped.
     */
    private Queue<Card> faceUpCards = new ArrayDeque<>();

    @Inject
    public FaceUpCardsHandler(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void gameStarted(GameStartEvent event) {
        faceUpCards.clear();
    }

    /**
     * Dispatch a {@link net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent} for every two cards flipped.
     */
    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        faceUpCards.offer(event.getCard());

        while (faceUpCards.size() >= 2) {
            Card firstCard = faceUpCards.poll();
            Card secondCard = faceUpCards.poll();

            eventDispatcher.dispatch(new PairFlipUpEvent(this, firstCard, secondCard));
        }
    }

}
