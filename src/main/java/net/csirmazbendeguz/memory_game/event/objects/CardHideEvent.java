package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

public class CardHideEvent extends CardEvent {

    public CardHideEvent(Object source, Card card) {
        super(source, card);
    }

}
