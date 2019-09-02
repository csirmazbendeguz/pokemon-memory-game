package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

import java.util.EventObject;

/**
 * Event object for dispatching information about a flipped up card.
 */
public class CardFlipUpEvent extends EventObject {

    private Card card;

    public CardFlipUpEvent(Object source, Card card) {
        super(source);
    }

    public Card getCard() {
        return card;
    }

}
