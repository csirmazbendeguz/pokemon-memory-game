package net.csirmazbendeguz.memory_game.state;

import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;

@Singleton
public class Board implements GameStartListener {

    private int dimension;

    private Card[][] cards;

    @Override
    public void gameStarted(GameStartEvent event) {
        dimension = event.getDimension();
        cards = event.getCards();
    }

    /**
     * Return true if all the cards have been removed from the board.
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
        return dimension;
    }

}
