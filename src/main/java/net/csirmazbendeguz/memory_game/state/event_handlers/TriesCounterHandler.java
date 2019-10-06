package net.csirmazbendeguz.memory_game.state.event_handlers;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.state.TriesCounter;

public class TriesCounterHandler implements GameStartListener {

    private TriesCounter triesCounter;

    @Inject
    public TriesCounterHandler(TriesCounter triesCounter) {
        this.triesCounter = triesCounter;
    }

    @Override
    public void gameStarted(GameStartEvent event) {
        triesCounter.reset();
    }

}
