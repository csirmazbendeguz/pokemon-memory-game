package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.CardFlipDownEvent;

import java.util.EventListener;

public interface CardFlipDownListener extends EventListener {

    void cardFlippedDown(CardFlipDownEvent event);

}
