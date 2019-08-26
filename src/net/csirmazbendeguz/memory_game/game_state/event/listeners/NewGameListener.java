package net.csirmazbendeguz.memory_game.game_state.event.listeners;

import net.csirmazbendeguz.memory_game.game_state.event.objects.NewGameEvent;

import java.util.EventListener;

public interface NewGameListener extends EventListener {

    void newGameStarted(NewGameEvent event);

}
