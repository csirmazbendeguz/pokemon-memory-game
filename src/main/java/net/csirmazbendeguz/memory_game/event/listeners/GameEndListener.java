package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;

import java.util.EventListener;

/**
 * Event listener for finishing a game.
 */
public interface GameEndListener extends EventListener {

    /**
     * Called when a game is finished.
     */
    void gameEnded(GameEndEvent event);

}
