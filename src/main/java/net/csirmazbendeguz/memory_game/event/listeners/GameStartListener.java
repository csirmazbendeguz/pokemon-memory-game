package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;

import java.util.EventListener;

public interface GameStartListener extends EventListener {

    /**
     * Called when a new game is started.
     */
    void gameStarted(GameStartEvent event);

}
