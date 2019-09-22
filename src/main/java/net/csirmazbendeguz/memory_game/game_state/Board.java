package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * The board state.
 */
public class Board implements CardFlipUpListener {

    /**
     * The memory card board.
     */
    private Card[][] board;

    /**
     * The cards facing up, in the order they were flipped.
     */
    private Queue<Card> faceUpCards;

    /**
     * The tries counter.
     */
    private TriesCounter triesCounter;

    @Inject
    public Board(TriesCounter triesCounter, EventDispatcher eventDispatcher) {
        this.triesCounter = triesCounter;
        eventDispatcher.addListener(CardFlipUpEvent.class, this);
    }

    /**
     * Initialize the board.
     */
    void init(Card[][] board) {
        this.board = board;
        faceUpCards = new ArrayDeque<>();
    }

    /**
     * Check if all the cards are hidden.
     */
    boolean areAllCardsHidden() {
        for (Card[] cards : board) {
            for (Card card : cards) {
                if (card.isVisible()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Store the flipped up card, check for pairs and fire the card actions accordingly.
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
