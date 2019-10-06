package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.state.Card;

@Listener(CardHideListener.class)
public class CardHideEvent extends CardEvent {

    public CardHideEvent(Object source, Card card) {
        super(source, card);
    }

}
