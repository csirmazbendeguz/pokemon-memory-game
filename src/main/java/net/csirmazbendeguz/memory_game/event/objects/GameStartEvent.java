package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

import java.util.EventObject;

/**
 * Event object for dispatching information about a new game.
 */
public class GameStartEvent extends EventObject {

    private int dimension;

    private Card[][] cards;

    public GameStartEvent(Object source, int dimension, Card[][] cards) {
        super(source);
        this.dimension = dimension;
        this.cards = cards;
    }

    public int getDimension() {
        return dimension;
    }

    public Card[][] getCards() {
        return cards;
    }

}
