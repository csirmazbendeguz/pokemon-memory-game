package net.csirmazbendeguz.memory_game.game_state;

import java.util.ArrayDeque;
import java.util.Queue;

public class Board {

    private int dimension;

    private Card[][] board;

    private Queue<Card> faceUpCards;

    public Board(int dimension, Card[][] board) {
        this.dimension = dimension;
        this.board = board;
        faceUpCards = new ArrayDeque<>();
    }

    public int getDimension() {
        return dimension;
    }

    public Card[][] getBoard() {
        return board;
    }

    public void flipUp(Card card) {
        card.flipUp();
        faceUpCards.offer(card);
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

    public void checkPairs() {
        TriesCounter triesCounter = TriesCounter.getInstance();

        while (faceUpCards.size() >= 2) {
            triesCounter.increase();

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
