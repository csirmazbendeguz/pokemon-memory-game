package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;

import java.util.EventListener;

/**
 * Event listener for flipping up a card.
 */
public interface CardFlipUpListener extends EventListener {

    /**
     * Called when a card is flipped up.
     */
    void cardFlippedUp(CardFlipUpEvent event);

}
