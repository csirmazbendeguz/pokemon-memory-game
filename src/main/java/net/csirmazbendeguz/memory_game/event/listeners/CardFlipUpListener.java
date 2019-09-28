package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;

import java.util.EventListener;

public interface CardFlipUpListener extends EventListener {

    void cardFlippedUp(CardFlipUpEvent event);

}
