package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

public class CardFlipUpEvent extends CardEvent {

    public CardFlipUpEvent(Object source, Card card) {
        super(source, card);
    }

}
