package net.csirmazbendeguz.memory_game.game_state.event.listeners;

import net.csirmazbendeguz.memory_game.game_state.event.objects.WinEvent;

import java.util.EventListener;

public interface WinListener extends EventListener {

    void gameWon(WinEvent event);

}
