package net.csirmazbendeguz.memory_game.game_state.event_handlers;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;

import java.util.ArrayDeque;
import java.util.Queue;

public class PairHandler implements GameStartListener, CardFlipUpListener {

    /**
     * The cards facing up in the order they were flipped.
     */
    private Queue<Card> faceUpCards = new ArrayDeque<>();

    private TriesCounter triesCounter;

    @Inject
    public PairHandler(TriesCounter triesCounter) {
        this.triesCounter = triesCounter;
    }

    @Override
    public void gameStarted(GameStartEvent event) {
        faceUpCards.clear();
    }

    /**
     * Check for pairs.
     */
    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        faceUpCards.offer(event.getCard());

        while (faceUpCards.size() >= 2) {
            triesCounter.increment();

            Card card1 = faceUpCards.poll();
            Card card2 = faceUpCards.poll();

            if (card1.isPairOf(card2)) {
                card1.hide();
                card2.hide();
            } else {
                card1.flipDown();
                card2.flipDown();
            }
        }
    }

}
