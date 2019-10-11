package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.state.Card;

import java.util.EventObject;

@Listener(GameStartListener.class)
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
