package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipDownListener;
import net.csirmazbendeguz.memory_game.state.Card;

@Listener(CardFlipDownListener.class)
public class CardFlipDownEvent extends CardEvent {

    public CardFlipDownEvent(Object source, Card card) {
        super(source, card);
    }

}
