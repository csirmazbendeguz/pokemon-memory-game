package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.state.Card;

@Listener(CardFlipUpListener.class)
public class CardFlipUpEvent extends CardEvent {

    public CardFlipUpEvent(Object source, Card card) {
        super(source, card);
    }

}
