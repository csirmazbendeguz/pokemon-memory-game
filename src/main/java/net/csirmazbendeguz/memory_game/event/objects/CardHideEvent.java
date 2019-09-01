package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

import java.util.EventObject;

/**
 * Event object for dispatching information about a hidden card.
 */
public class CardHideEvent extends EventObject {

    private Card card;

    public CardHideEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
