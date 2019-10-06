package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.PairFlipUpListener;
import net.csirmazbendeguz.memory_game.state.Card;

import java.util.EventObject;

@Listener(PairFlipUpListener.class)
public class PairFlipUpEvent extends EventObject {

    private Card firstCard, secondCard;

    public PairFlipUpEvent(Object source, Card firstCard, Card secondCard) {
        super(source);
        this.firstCard = firstCard;
        this.secondCard = secondCard;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

}
