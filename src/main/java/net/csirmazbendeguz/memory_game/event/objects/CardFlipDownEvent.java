package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

public class CardFlipDownEvent extends CardEvent {

    public CardFlipDownEvent(Object source, Card card) {
        super(source, card);
    }

}
