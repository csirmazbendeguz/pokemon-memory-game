package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.state.Card;

import java.util.EventObject;

public abstract class CardEvent extends EventObject {

    private Card card;

    CardEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
