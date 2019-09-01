package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;

import java.util.EventListener;

/**
 * Event listener for starting a new game.
 */
public interface GameStartListener extends EventListener {

    /**
     * Called when a new game is started.
     */
    void gameStarted(GameStartEvent event);

}
