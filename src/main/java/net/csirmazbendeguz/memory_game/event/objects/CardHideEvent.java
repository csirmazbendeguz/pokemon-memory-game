package net.csirmazbendeguz.memory_game.event.objects;

import java.util.EventObject;

/**
 * Event object for dispatching information about a hidden card.
 */
public class CardHideEvent extends EventObject {

    public CardHideEvent(Object source) {
        super(source);
    }

}
