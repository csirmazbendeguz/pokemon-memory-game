package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;

import java.util.EventListener;

public interface CardHideListener extends EventListener {

    void cardHidden(CardHideEvent event);

}
