package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.PairFlipUpEvent;

import java.util.EventListener;

public interface PairFlipUpListener extends EventListener {

    void pairFlippedUp(PairFlipUpEvent event);

}
