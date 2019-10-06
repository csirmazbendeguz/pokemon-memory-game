package net.csirmazbendeguz.memory_game.state;

import com.google.inject.Singleton;

@Singleton
public class Board {

    private Card[][] cards;

    public void init(Card[][] cards) {
        this.cards = cards;
    }

    /**
     * Check if all the cards have been removed from the board.
     */
    public boolean isEmpty() {
        for (Card[] row : cards) {
            for (Card card : row) {
                if (card.isVisible()) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getDimension() {
        return cards.length;
    }

    public Card getCard(int row, int column) {
        return cards[row][column];
    }

}