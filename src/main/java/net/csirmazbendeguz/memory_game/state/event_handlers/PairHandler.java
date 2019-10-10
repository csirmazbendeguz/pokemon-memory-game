package net.csirmazbendeguz.memory_game.state.event_handlers;

import net.csirmazbendeguz.memory_game.event.listeners.PairFlipUpListener;
import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;
import net.csirmazbendeguz.memory_game.state.Card;

public class PairHandler implements PairFlipUpListener {

    /**
     * Check for pairs.
     */
    @Override
    public void pairFlippedUp(PairFlipUpEvent event) {
        Card firstCard = event.getFirstCard();
        Card secondCard = event.getSecondCard();

        if (firstCard.isPairOf(secondCard)) {
            firstCard.hide();
            secondCard.hide();
        } else {
            firstCard.flipDown();
            secondCard.flipDown();
        }
    }

}
