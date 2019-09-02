package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;

import java.util.EventListener;

/**
 * Event listener for hiding a card.
 */
public interface CardHideListener extends EventListener {

    /**
     * Called when a card is hidden.
     */
    void cardHidden(CardHideEvent event);

}
