package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * The board state.
 */
public class Board implements CardFlipUpListener {

    private int dimension;

    private Card[][] board;

    private Queue<Card> faceUpCards;

    private TriesCounter triesCounter;

    public Board(int dimension, Card[][] board, TriesCounter triesCounter, EventDispatcher eventDispatcher) {
        this.dimension = dimension;
        this.board = board;
        faceUpCards = new ArrayDeque<>();
        this.triesCounter = triesCounter;
        eventDispatcher.addListener(CardFlipUpEvent.class, this);
    }

    public int getDimension() {
        return dimension;
    }

    public Card[][] getBoard() {
        return board;
    }

    public boolean isGameWon() {
        for (Card[] cards : board) {
            for (Card card : cards) {
                if (card.isVisible()) {
                    return false;
                }
            }
        }

        return true;
    }

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
