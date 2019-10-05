package net.csirmazbendeguz.memory_game.event.listeners;

import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;

import java.util.EventListener;

public interface GameEndListener extends EventListener {

    /**
     * Called when a game is finished, i.e. all the cards got removed from the board.
     */
    void gameEnded(GameEndEvent event);

}
